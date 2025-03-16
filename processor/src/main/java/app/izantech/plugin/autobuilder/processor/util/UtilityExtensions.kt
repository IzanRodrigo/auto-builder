package app.izantech.plugin.autobuilder.processor.util

import java.util.Locale

internal fun String.capitalizeCompat() =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

internal inline fun <T> T.runIf(condition: Boolean, block: T.() -> T) =
    if (condition) block() else this

internal inline fun <T, U> T.runIfNotNull(any: U?, block: T.(U) -> T) =
    if (any != null) block(any) else this
