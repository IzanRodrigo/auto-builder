import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import app.izantech.plugin.autobuilder.annotation.AutoBuilder
import app.izantech.plugin.autobuilder.annotation.DefaultValue

@AutoBuilder
interface TestInterface {
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
    val generatedObject: ModelWithDefaults
    val lambda: () -> Unit
    val lambdaWithAnnotation: @Composable () -> Unit
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

data class ComplexObject(
    val string: String = "Default string",
    val int: Int = 42,
    val double: Double = 3.1416,
)

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
