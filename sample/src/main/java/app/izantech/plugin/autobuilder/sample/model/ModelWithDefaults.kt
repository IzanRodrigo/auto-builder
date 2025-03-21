package app.izantech.plugin.autobuilder.sample.model

import app.izantech.plugin.autobuilder.annotation.AutoBuilder
import app.izantech.plugin.autobuilder.annotation.DefaultValue

@AutoBuilder
interface ModelWithDefaults {
    val stringWithDefault: String
        @DefaultValue get() = "Default String"

    val intWithDefault: Int
        @DefaultValue get() = 42

    val doubleWithDefault: Double
        @DefaultValue get() = 3.1416

    val charSequenceWithDefault: CharSequence
        @DefaultValue get() = "Default CharSequence"

    val complexObjectWithDefault: ComplexObject
        @DefaultValue get() = ComplexObject()
}
