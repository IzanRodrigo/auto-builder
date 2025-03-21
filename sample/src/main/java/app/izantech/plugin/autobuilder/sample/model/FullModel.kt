package app.izantech.plugin.autobuilder.sample.model

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import app.izantech.plugin.autobuilder.annotation.AutoBuilder
import app.izantech.plugin.autobuilder.annotation.DefaultValue
import app.izantech.plugin.autobuilder.annotation.Lateinit

data class ComplexObject(
    val string: String = "Default string",
    val int: Int = 42,
    val double: Double = 3.1416,
)

interface ArrayProperties {
    val array: Array<String>
    val intArray: IntArray
    val floatArray: FloatArray
    val doubleArray: DoubleArray
    val charArray: CharArray
    val shortArray: ShortArray
    val longArray: LongArray
    val byteArray: ByteArray
    val booleanArray: BooleanArray
    val optArray: Array<String>?
    val optIntArray: IntArray?
    val optFloatArray: FloatArray?
    val optDoubleArray: DoubleArray?
    val optCharArray: CharArray?
    val optShortArray: ShortArray?
    val optLongArray: LongArray?
    val optByteArray: ByteArray?
    val optBooleanArray: BooleanArray?
}

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
    val list: List<String>
    val set: Set<String>
    val map: Map<String, Int>
    val annotatedString: AnnotatedString

    @Lateinit val lateinitProperty: ComplexObject

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
    val optSet: Set<String>?
    val optMap: Map<String, Int>?
    val annotatedString: AnnotatedString?
    val optComplexObject: ComplexObject?
    val optGeneratedObject: ModelWithDefaults?
    val optLambda: (() -> Unit)?
    val optLambdaWithAnnotation: @Composable (() -> Unit)?
}

@AutoBuilder(inheritedProperties = true)
interface FullModel : NonOptionalProperties, OptionalProperties, ArrayProperties
