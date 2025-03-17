package app.izantech.plugin.autobuilder.sample

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import app.izantech.plugin.autobuilder.annotation.AutoBuilder
import app.izantech.plugin.autobuilder.annotation.DefaultValue

data class ComplexObject(
    val string: String = "Default string",
    val int: Int = 42,
    val double: Double = 3.1416,
)

interface NonOptionalProperties {
    val charSequence: CharSequence
    val string: String
    val int: Int
    val double: Double
    val char: Char
    val short: Short
    val long: Long
    @get:FloatRange(0.0, 1.0) val float: Float
    val byte: Byte
    val number: Number
    val boolean: Boolean
    val array: Array<String>
    val list: List<String>

    val complexObject: ComplexObject
        @DefaultValue get() = ComplexObject()

    val generatedObject: ModelWithDefaults
        @DefaultValue get() = ModelWithDefaults()

    val lambda: () -> Unit
        @DefaultValue get() = {}

    val lambdaWithAnnotation: @Composable () -> Unit
        @DefaultValue get() = {}
}

interface OptionalProperties {
    val optCharSequence: CharSequence?
    val optString: String?
    val optInt: Int?
    val optDouble: Double?
    val optChar: Char?
    val optShort: Short?
    val optLong: Long?
    @get:FloatRange(0.0, 1.0) val optFloat: Float?
    val optByte: Byte?
    val optNumber: Number?
    val optBoolean: Boolean?
    val optArray: Array<String>?
    val optList: List<String>?
    val optComplexObject: ComplexObject?
    val optGeneratedObject: ModelWithDefaults?
    val optLambda: (() -> Unit)?
    val optLambdaWithAnnotation: @Composable (() -> Unit)?
}

@AutoBuilder(inheritedProperties = true)
interface FullModel : NonOptionalProperties, OptionalProperties

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

fun main() {
    // Test FullModel
    val modelWithDefaults = ModelWithDefaults()
    val model = FullModel {
        string = "Hello, World!"
        int = 42
        double = 3.14
        complexObject = ComplexObject(
            string = "Complex object",
            int = 123,
            double = 2.718,
        )
        generatedObject = modelWithDefaults
        lambda = { println("Lambda") }
        lambdaWithAnnotation = @Composable { println("Lambda with annotation") }
    }
    val sameModel = model.copy()
    println(model)
    println("model == sameModel: ${model == sameModel}")
    println()

    // Test copy method
    val copy = model.copy {
        optString = "Optional string"
        optInt = 123
        optDouble = 2.718
        optComplexObject = ComplexObject(
            string = "Optional complex object",
            int = 123,
            double = 2.718,
        )
        optGeneratedObject = modelWithDefaults.copy {
            string = "Optional small model"
            int = 123
            double = 2.718
        }
        optLambda = { println("Optional lambda") }
    }
    println(copy)
    println("model == copy: ${model == copy}")
    println()
}
