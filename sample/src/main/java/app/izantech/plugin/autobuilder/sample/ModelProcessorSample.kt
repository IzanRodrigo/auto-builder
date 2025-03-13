package app.izantech.plugin.autobuilder.sample

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import app.izantech.plugin.autobuilder.annotation.AutoBuilder
import app.izantech.plugin.autobuilder.annotation.AutoBuilder.Property

data class ComplexObject(
    val string: String = "Default string",
    val int: Int = 42,
    val double: Double = 3.1416,
)

interface NonOptionalProperties {
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
    val generatedObject: ModelWithDefaults
    val lambda: () -> Unit
}

interface OptionalProperties {
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
}

@AutoBuilder(inheritedProperties = true)
interface FullModel : NonOptionalProperties, OptionalProperties

@AutoBuilder
interface ModelWithDefaults {
    @Property(defaultValue = "Default String")
    val stringWithDefault: String

    @Property(defaultValue = "42")
    val intWithDefault: Int

    @Property(defaultValue = "3.1416")
    val doubleWithDefault: Double

    @Property(defaultValue = "Default CharSequence")
    val charSequenceWithDefault: CharSequence

    @Property(defaultValue = "ComplexObject()")
    val complexObjectWithDefault: ComplexObject
}

@AutoBuilder
interface EqualityModel {
    val a: String
    val b: Int
}

@AutoBuilder
interface ModelWithFunctions {
    val regularFunction: () -> Unit
    val suspendFunction: suspend () -> Unit
    val composableFunction: @Composable () -> Unit
}

fun main() {
    // Test equality
    val eq1 = EqualityModel {
        a = "Test"
        b = 42
    }
    val eq2 = EqualityModel {
        b = 42
        a = "Test"
    }
    println(eq1)
    println(eq2)
    println("eq1 == eq2: ${eq1 == eq2}")
    println()

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

    // Test functions.
    val modelWithFunctions = ModelWithFunctions {
        regularFunction = { println("Regular function") }
        suspendFunction = suspend { println("Suspend function") }
        composableFunction = @Composable { println("Composable function") }
    }
    val modelWithFunctionsCopy = modelWithFunctions.copy()
    println(modelWithFunctions)
    println(modelWithFunctionsCopy)
    println("modelWithFunctions == modelWithFunctionsCopy: ${modelWithFunctions == modelWithFunctionsCopy}")
}
