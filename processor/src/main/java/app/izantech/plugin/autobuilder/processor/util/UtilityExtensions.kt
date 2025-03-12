package app.izantech.plugin.autobuilder.processor.util

import java.util.Locale

internal fun <T> Iterable<T>.prettyPrint(transform: (T) -> String = { it.toString() }) =
    joinToString(
        prefix = "\n\t\t",
        separator = "\n\t\t",
        postfix = "\n\t",
        transform = transform,
    )

internal fun String.capitalizeCompat() =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
