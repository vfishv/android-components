/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package mozilla.components.browser.session.engine.middleware

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mozilla.components.browser.session.Session
import mozilla.components.browser.session.engine.getOrCreateEngineSession
import mozilla.components.browser.state.action.BrowserAction
import mozilla.components.browser.state.action.EngineAction
import mozilla.components.browser.state.selector.findTabOrCustomTab
import mozilla.components.browser.state.state.BrowserState
import mozilla.components.browser.state.state.TabSessionState
import mozilla.components.concept.engine.Engine
import mozilla.components.concept.engine.EngineSession
import mozilla.components.lib.state.Middleware
import mozilla.components.lib.state.MiddlewareStore
import mozilla.components.support.base.log.logger.Logger

/**
 * [Middleware] responsible for delegating calls to the appropriate [EngineSession] instance for
 * actions like [EngineAction.LoadUrlAction].
 */
internal class EngineDelegateMiddleware(
    private val engine: Engine,
    private val sessionLookup: (String) -> Session?,
    private val scope: CoroutineScope
) : Middleware<BrowserState, BrowserAction> {
    private val logger = Logger("EngineSessionMiddleware")

    @Suppress("LongMethod")
    override fun invoke(
        store: MiddlewareStore<BrowserState, BrowserAction>,
        next: (BrowserAction) -> Unit,
        action: BrowserAction
    ) {
        when (action) {
            is EngineAction.LoadUrlAction -> scope.launch {
                val tab = store.state.findTabOrCustomTab(action.sessionId) ?: return@launch
                val parentEngineSession = if (tab is TabSessionState) {
                    tab.parentId?.let { store.state.findTabOrCustomTab(it)?.engineState?.engineSession }
                } else {
                    null
                }

                if (tab.engineState.engineSession == null && tab.content.url == action.url) {
                    // This tab does not have an engine session and we are asked to load the URL this
                    // session is already pointing to. Creating an EngineSession will do exactly
                    // that in the linking step. So let's do that. Otherwise we would load the URL
                    // twice.
                    store.dispatch(EngineAction.CreateEngineSessionAction(action.sessionId))
                    return@launch
                }

                getOrCreateEngineSession(
                    engine,
                    logger,
                    sessionLookup,
                    store,
                    tabId = action.sessionId
                )?.loadUrl(
                    url = action.url,
                    parent = parentEngineSession,
                    flags = action.flags,
                    additionalHeaders = action.additionalHeaders
                )
            }

            is EngineAction.LoadDataAction -> scope.launch {
                val engineSession = getOrCreateEngineSession(
                    engine,
                    logger,
                    sessionLookup,
                    store,
                    tabId = action.sessionId
                )
                engineSession?.loadData(action.data, action.mimeType, action.encoding)
            }

            is EngineAction.ReloadAction -> scope.launch {
                val engineSession = getOrCreateEngineSession(
                    engine,
                    logger,
                    sessionLookup,
                    store,
                    tabId = action.sessionId
                )
                engineSession?.reload(action.flags)
            }

            is EngineAction.GoBackAction -> scope.launch {
                val engineSession = getOrCreateEngineSession(
                    engine,
                    logger,
                    sessionLookup,
                    store,
                    tabId = action.sessionId
                )
                engineSession?.goBack()
            }

            is EngineAction.GoForwardAction -> scope.launch {
                val engineSession = getOrCreateEngineSession(
                    engine,
                    logger,
                    sessionLookup,
                    store,
                    tabId = action.sessionId
                )
                engineSession?.goForward()
            }

            is EngineAction.GoToHistoryIndexAction -> scope.launch {
                val engineSession = getOrCreateEngineSession(
                    engine,
                    logger,
                    sessionLookup,
                    store,
                    tabId = action.sessionId
                )
                engineSession?.goToHistoryIndex(action.index)
            }

            is EngineAction.ToggleDesktopModeAction -> scope.launch {
                val engineSession = getOrCreateEngineSession(
                    engine,
                    logger,
                    sessionLookup,
                    store,
                    tabId = action.sessionId
                )
                engineSession?.toggleDesktopMode(action.enable, reload = true)
            }

            is EngineAction.ExitFullscreenModeAction -> scope.launch {
                val engineSession = getOrCreateEngineSession(
                    engine,
                    logger,
                    sessionLookup,
                    store,
                    tabId = action.sessionId
                )
                engineSession?.exitFullScreenMode()
            }

            is EngineAction.ClearDataAction -> scope.launch {
                val engineSession = getOrCreateEngineSession(
                    engine,
                    logger,
                    sessionLookup,
                    store,
                    tabId = action.sessionId
                )
                engineSession?.clearData(action.data)
            }

            else -> next(action)
        }
    }
}
