[android-components](../../index.md) / [mozilla.components.feature.downloads](../index.md) / [SimpleDownloadDialogFragment](./index.md)

# SimpleDownloadDialogFragment

`class SimpleDownloadDialogFragment : `[`DownloadDialogFragment`](../-download-dialog-fragment/index.md) [(source)](https://github.com/mozilla-mobile/android-components/blob/master/components/feature/downloads/src/main/java/mozilla/components/feature/downloads/SimpleDownloadDialogFragment.kt#L35)

A confirmation dialog to be called before a download is triggered.
Meant to be used in collaboration with [DownloadsFeature](../-downloads-feature/index.md)

[SimpleDownloadDialogFragment](./index.md) is the default dialog used by DownloadsFeature if you don't provide a value.
It is composed by a title, a negative and a positive bottoms. When the positive button is clicked
the download is triggered.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SimpleDownloadDialogFragment()`<br>A confirmation dialog to be called before a download is triggered. Meant to be used in collaboration with [DownloadsFeature](../-downloads-feature/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [onCancelDownload](../-download-dialog-fragment/on-cancel-download.md) | `var onCancelDownload: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onStartDownload](../-download-dialog-fragment/on-start-download.md) | `var onStartDownload: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>A callback to trigger a download, call it when you are ready to start a download. For instance, a valid use case can be in confirmation dialog, after the positive button is clicked, this callback must be called. |

### Functions

| Name | Summary |
|---|---|
| [onCreateDialog](on-create-dialog.md) | `fun onCreateDialog(savedInstanceState: <ERROR CLASS>?): <ERROR CLASS>` |

### Inherited Functions

| Name | Summary |
|---|---|
| [setDownload](../-download-dialog-fragment/set-download.md) | `fun setDownload(download: `[`DownloadState`](../../mozilla.components.browser.state.state.content/-download-state/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add the metadata of this download object to the arguments of this fragment. |

### Companion Object Properties

| Name | Summary |
|---|---|
| [KEY_DOWNLOAD_TEXT](-k-e-y_-d-o-w-n-l-o-a-d_-t-e-x-t.md) | `const val KEY_DOWNLOAD_TEXT: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [KEY_THEME_ID](-k-e-y_-t-h-e-m-e_-i-d.md) | `const val KEY_THEME_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [KEY_TITLE_TEXT](-k-e-y_-t-i-t-l-e_-t-e-x-t.md) | `const val KEY_TITLE_TEXT: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [newInstance](new-instance.md) | `fun newInstance(dialogTitleText: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = R.string.mozac_feature_downloads_dialog_title2, downloadButtonText: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = R.string.mozac_feature_downloads_dialog_download, themeResId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, promptsStyling: `[`PromptsStyling`](../-downloads-feature/-prompts-styling/index.md)`? = null): `[`SimpleDownloadDialogFragment`](./index.md)<br>A builder method for creating a [SimpleDownloadDialogFragment](./index.md) |

### Extension Functions

| Name | Summary |
|---|---|
| [consumeFrom](../../mozilla.components.lib.state.ext/androidx.fragment.app.-fragment/consume-from.md) | `fun <S : `[`State`](../../mozilla.components.lib.state/-state.md)`, A : `[`Action`](../../mozilla.components.lib.state/-action.md)`> Fragment.consumeFrom(store: `[`Store`](../../mozilla.components.lib.state/-store/index.md)`<`[`S`](../../mozilla.components.lib.state.ext/androidx.fragment.app.-fragment/consume-from.md#S)`, `[`A`](../../mozilla.components.lib.state.ext/androidx.fragment.app.-fragment/consume-from.md#A)`>, block: (`[`S`](../../mozilla.components.lib.state.ext/androidx.fragment.app.-fragment/consume-from.md#S)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Helper extension method for consuming [State](../../mozilla.components.lib.state/-state.md) from a [Store](../../mozilla.components.lib.state/-store/index.md) sequentially in order inside a [Fragment](#). The [block](../../mozilla.components.lib.state.ext/androidx.fragment.app.-fragment/consume-from.md#mozilla.components.lib.state.ext$consumeFrom(androidx.fragment.app.Fragment, mozilla.components.lib.state.Store((mozilla.components.lib.state.ext.consumeFrom.S, mozilla.components.lib.state.ext.consumeFrom.A)), kotlin.Function1((mozilla.components.lib.state.ext.consumeFrom.S, kotlin.Unit)))/block) function will get invoked for every [State](../../mozilla.components.lib.state/-state.md) update. |
| [loadResourceAsString](../../mozilla.components.support.test.file/kotlin.-any/load-resource-as-string.md) | `fun `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`.loadResourceAsString(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Loads a file from the resources folder and returns its content as a string object. |
