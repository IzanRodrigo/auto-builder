package app.izantech.plugin.autobuilder.processor.util

internal fun <T> Iterable<T>.prettyPrint(transform: (T) -> String = { it.toString() }) =
    joinToString(
        prefix = "\n\t\t",
        separator = "\n\t\t",
        postfix = "\n\t",
        transform = transform,
    )
