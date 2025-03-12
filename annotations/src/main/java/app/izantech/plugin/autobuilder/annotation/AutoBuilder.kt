package app.izantech.plugin.autobuilder.annotation

/**
 * This annotation is used to generate a builder class for the annotated interface.
 *
 * @param inheritedProperties If true, the builder will include properties from super interfaces. By default is false since obtaining superclass properties is expensive.
 */
@Target(AnnotationTarget.CLASS)
annotation class AutoBuilder(val inheritedProperties: Boolean = false) {

    /**
     * This annotation is used to customize the properties of the generated builder.
     * To add default values to the properties, use the defaultValue parameter.
     * This is required because KSP doesn't allow reading the default value of a property.
     *
     * @param defaultValue The default value for the property. By default the value is inferred from the property type.
     */
    @Target(AnnotationTarget.PROPERTY)
    annotation class Property(
        val defaultValue: String = "",
        val ignored: Boolean = false,
    )
}
