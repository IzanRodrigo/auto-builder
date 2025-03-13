package app.izantech.plugin.autobuilder.annotation

/**
 * This annotation is used to generate a builder class for the annotated interface.
 *
 * @param inheritedProperties If true, the builder will include properties from super interfaces. By default is false since obtaining superclass properties is expensive.
 */
@Target(AnnotationTarget.CLASS)
annotation class AutoBuilder(
    val inheritedProperties: Boolean = false,
)

/**
 * This annotation is used to ignore a property when generating a builder class.
 */
@Target(AnnotationTarget.PROPERTY)
annotation class Ignored

/**
 * This annotation is used to specify a default value for a property when generating a builder class.
 */
@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class DefaultValue
