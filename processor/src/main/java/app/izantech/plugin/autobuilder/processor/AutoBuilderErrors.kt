package app.izantech.plugin.autobuilder.processor

internal object AutoBuilderErrors {
    const val NOT_PUBLIC_INTERFACE = "This model definition must be a public interface containing at least one property."
    const val EMPTY_INTERFACE = "This model definition must contain at least one property."
}
