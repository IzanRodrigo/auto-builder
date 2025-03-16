@file:Suppress(
	"ConstPropertyName",
	"MemberVisibilityCanBePrivate",
	"NEWER_VERSION_IN_SINCE_KOTLIN",
	"RedundantNullableReturnType",
	"RedundantVisibilityModifier",
	"unused",
)

import androidx.`annotation`.FloatRange
import androidx.compose.runtime.Composable
import java.util.Objects.hash
import kotlin.Any
import kotlin.Array
import kotlin.Boolean
import kotlin.Byte
import kotlin.Char
import kotlin.CharSequence
import kotlin.Double
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.Number
import kotlin.PublishedApi
import kotlin.Short
import kotlin.SinceKotlin
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.apply
import kotlin.collections.List
import kotlin.jvm.JvmSynthetic

private class TestInterfaceImpl(
  override val array: Array<String>,
  override val boolean: Boolean,
  override val byte: Byte,
  override val char: Char,
  override val charSequence: CharSequence,
  override val complexObject: ComplexObject,
  override val double: Double,
  @get:FloatRange(
    from = 0.0,
    to = 1.0,
    fromInclusive = true,
    toInclusive = true,
  )
  override val float: Float,
  override val generatedObject: ModelWithDefaults,
  override val int: Int,
  override val lambda: () -> Unit,
  override val lambdaWithAnnotation: @Composable () -> Unit,
  override val list: List<String>,
  override val long: Long,
  override val number: Number,
  override val optArray: Array<String>?,
  override val optBoolean: Boolean?,
  override val optByte: Byte?,
  override val optChar: Char?,
  override val optCharSequence: CharSequence?,
  override val optComplexObject: ComplexObject?,
  override val optDouble: Double?,
  @get:FloatRange(
    from = 0.0,
    to = 1.0,
    fromInclusive = true,
    toInclusive = true,
  )
  override val optFloat: Float?,
  override val optGeneratedObject: ModelWithDefaults?,
  override val optInt: Int?,
  override val optLambda: (() -> Unit)?,
  override val optLambdaWithAnnotation: @Composable (() -> Unit)?,
  override val optList: List<String>?,
  override val optLong: Long?,
  override val optNumber: Number?,
  override val optShort: Short?,
  override val optString: String?,
  override val short: Short,
  override val string: String,
) : TestInterface {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as TestInterfaceImpl
    return this.array.contentEquals(other.array) && this.boolean == other.boolean && this.byte == other.byte && this.char == other.char && this.charSequence == other.charSequence && this.complexObject == other.complexObject && this.double == other.double && this.float == other.float && this.generatedObject == other.generatedObject && this.int == other.int && this.lambda == other.lambda && this.lambdaWithAnnotation == other.lambdaWithAnnotation && this.list == other.list && this.long == other.long && this.number == other.number && this.optArray.contentEquals(other.optArray) && this.optBoolean == other.optBoolean && this.optByte == other.optByte && this.optChar == other.optChar && this.optCharSequence == other.optCharSequence && this.optComplexObject == other.optComplexObject && this.optDouble == other.optDouble && this.optFloat == other.optFloat && this.optGeneratedObject == other.optGeneratedObject && this.optInt == other.optInt && this.optLambda == other.optLambda && this.optLambdaWithAnnotation == other.optLambdaWithAnnotation && this.optList == other.optList && this.optLong == other.optLong && this.optNumber == other.optNumber && this.optShort == other.optShort && this.optString == other.optString && this.short == other.short && this.string == other.string
  }

  override fun hashCode(): Int = hash(array, boolean, byte, char, charSequence, complexObject, double, float, generatedObject, int, lambda, lambdaWithAnnotation, list, long, number, optArray, optBoolean, optByte, optChar, optCharSequence, optComplexObject, optDouble, optFloat, optGeneratedObject, optInt, optLambda, optLambdaWithAnnotation, optList, optLong, optNumber, optShort, optString, short, string)

  override fun toString(): String = buildString {
    append("TestInterface(")
    append("array=${array.contentToString()}, ")
    append("boolean=$boolean, ")
    append("byte=$byte, ")
    append("char=$char, ")
    append("charSequence=$charSequence, ")
    append("complexObject=$complexObject, ")
    append("double=$double, ")
    append("float=$float, ")
    append("generatedObject=$generatedObject, ")
    append("int=$int, ")
    append("lambda=$lambda, ")
    append("lambdaWithAnnotation=$lambdaWithAnnotation, ")
    append("list=$list, ")
    append("long=$long, ")
    append("number=$number, ")
    append("optArray=${optArray.contentToString()}, ")
    append("optBoolean=$optBoolean, ")
    append("optByte=$optByte, ")
    append("optChar=$optChar, ")
    append("optCharSequence=$optCharSequence, ")
    append("optComplexObject=$optComplexObject, ")
    append("optDouble=$optDouble, ")
    append("optFloat=$optFloat, ")
    append("optGeneratedObject=$optGeneratedObject, ")
    append("optInt=$optInt, ")
    append("optLambda=$optLambda, ")
    append("optLambdaWithAnnotation=$optLambdaWithAnnotation, ")
    append("optList=$optList, ")
    append("optLong=$optLong, ")
    append("optNumber=$optNumber, ")
    append("optShort=$optShort, ")
    append("optString=$optString, ")
    append("short=$short, ")
    append("string=$string")
    append(")")
  }
}

public class TestInterfaceBuilder @PublishedApi internal constructor(
  source: TestInterface? = null,
) {
  public var array: Array<String> = source?.array ?: TestInterfaceDefaults.array
    @JvmSynthetic
    set

  public var boolean: Boolean = source?.boolean ?: TestInterfaceDefaults.boolean
    @JvmSynthetic
    set

  public var byte: Byte = source?.byte ?: TestInterfaceDefaults.byte
    @JvmSynthetic
    set

  public var char: Char = source?.char ?: TestInterfaceDefaults.char
    @JvmSynthetic
    set

  public var charSequence: CharSequence = source?.charSequence ?: TestInterfaceDefaults.charSequence
    @JvmSynthetic
    set

  public var complexObject: ComplexObject? = source?.complexObject
    @JvmSynthetic
    set

  public var double: Double = source?.double ?: TestInterfaceDefaults.double
    @JvmSynthetic
    set

  @get:FloatRange(
    from = 0.0,
    to = 1.0,
    fromInclusive = true,
    toInclusive = true,
  )
  public var float: Float = source?.float ?: TestInterfaceDefaults.float
    @JvmSynthetic
    set

  public var generatedObject: ModelWithDefaults? = source?.generatedObject
    @JvmSynthetic
    set

  public var int: Int = source?.int ?: TestInterfaceDefaults.int
    @JvmSynthetic
    set

  public var lambda: (() -> Unit)? = source?.lambda
    @JvmSynthetic
    set

  public var lambdaWithAnnotation: @Composable (() -> Unit)? = source?.lambdaWithAnnotation
    @JvmSynthetic
    set

  public var list: List<String> = source?.list ?: TestInterfaceDefaults.list
    @JvmSynthetic
    set

  public var long: Long = source?.long ?: TestInterfaceDefaults.long
    @JvmSynthetic
    set

  public var number: Number = source?.number ?: TestInterfaceDefaults.number
    @JvmSynthetic
    set

  public var optArray: Array<String>? = source?.optArray ?: TestInterfaceDefaults.optArray
    @JvmSynthetic
    set

  public var optBoolean: Boolean? = source?.optBoolean ?: TestInterfaceDefaults.optBoolean
    @JvmSynthetic
    set

  public var optByte: Byte? = source?.optByte ?: TestInterfaceDefaults.optByte
    @JvmSynthetic
    set

  public var optChar: Char? = source?.optChar ?: TestInterfaceDefaults.optChar
    @JvmSynthetic
    set

  public var optCharSequence: CharSequence? =
      source?.optCharSequence ?: TestInterfaceDefaults.optCharSequence
    @JvmSynthetic
    set

  public var optComplexObject: ComplexObject? =
      source?.optComplexObject ?: TestInterfaceDefaults.optComplexObject
    @JvmSynthetic
    set

  public var optDouble: Double? = source?.optDouble ?: TestInterfaceDefaults.optDouble
    @JvmSynthetic
    set

  @get:FloatRange(
    from = 0.0,
    to = 1.0,
    fromInclusive = true,
    toInclusive = true,
  )
  public var optFloat: Float? = source?.optFloat ?: TestInterfaceDefaults.optFloat
    @JvmSynthetic
    set

  public var optGeneratedObject: ModelWithDefaults? =
      source?.optGeneratedObject ?: TestInterfaceDefaults.optGeneratedObject
    @JvmSynthetic
    set

  public var optInt: Int? = source?.optInt ?: TestInterfaceDefaults.optInt
    @JvmSynthetic
    set

  public var optLambda: (() -> Unit)? = source?.optLambda ?: TestInterfaceDefaults.optLambda
    @JvmSynthetic
    set

  public var optLambdaWithAnnotation: @Composable (() -> Unit)? =
      source?.optLambdaWithAnnotation ?: TestInterfaceDefaults.optLambdaWithAnnotation
    @JvmSynthetic
    set

  public var optList: List<String>? = source?.optList ?: TestInterfaceDefaults.optList
    @JvmSynthetic
    set

  public var optLong: Long? = source?.optLong ?: TestInterfaceDefaults.optLong
    @JvmSynthetic
    set

  public var optNumber: Number? = source?.optNumber ?: TestInterfaceDefaults.optNumber
    @JvmSynthetic
    set

  public var optShort: Short? = source?.optShort ?: TestInterfaceDefaults.optShort
    @JvmSynthetic
    set

  public var optString: String? = source?.optString ?: TestInterfaceDefaults.optString
    @JvmSynthetic
    set

  public var short: Short = source?.short ?: TestInterfaceDefaults.short
    @JvmSynthetic
    set

  public var string: String = source?.string ?: TestInterfaceDefaults.string
    @JvmSynthetic
    set

  @SinceKotlin("99999999.9")
  public fun setArray(array: Array<String>): TestInterfaceBuilder = apply { this.array = array }

  @SinceKotlin("99999999.9")
  public fun setBoolean(boolean: Boolean): TestInterfaceBuilder = apply { this.boolean = boolean }

  @SinceKotlin("99999999.9")
  public fun setByte(byte: Byte): TestInterfaceBuilder = apply { this.byte = byte }

  @SinceKotlin("99999999.9")
  public fun setChar(char: Char): TestInterfaceBuilder = apply { this.char = char }

  @SinceKotlin("99999999.9")
  public fun setCharSequence(charSequence: CharSequence): TestInterfaceBuilder = apply { this.charSequence = charSequence }

  @SinceKotlin("99999999.9")
  public fun setComplexObject(complexObject: ComplexObject): TestInterfaceBuilder = apply { this.complexObject = complexObject }

  @SinceKotlin("99999999.9")
  public fun setDouble(double: Double): TestInterfaceBuilder = apply { this.double = double }

  @SinceKotlin("99999999.9")
  public fun setFloat(float: Float): TestInterfaceBuilder = apply { this.float = float }

  @SinceKotlin("99999999.9")
  public fun setGeneratedObject(generatedObject: ModelWithDefaults): TestInterfaceBuilder = apply { this.generatedObject = generatedObject }

  @SinceKotlin("99999999.9")
  public fun setInt(int: Int): TestInterfaceBuilder = apply { this.int = int }

  @SinceKotlin("99999999.9")
  public fun setLambda(lambda: () -> Unit): TestInterfaceBuilder = apply { this.lambda = lambda }

  @SinceKotlin("99999999.9")
  public fun setLambdaWithAnnotation(lambdaWithAnnotation: @Composable () -> Unit): TestInterfaceBuilder = apply { this.lambdaWithAnnotation = lambdaWithAnnotation }

  @SinceKotlin("99999999.9")
  public fun setList(list: List<String>): TestInterfaceBuilder = apply { this.list = list }

  @SinceKotlin("99999999.9")
  public fun setLong(long: Long): TestInterfaceBuilder = apply { this.long = long }

  @SinceKotlin("99999999.9")
  public fun setNumber(number: Number): TestInterfaceBuilder = apply { this.number = number }

  @SinceKotlin("99999999.9")
  public fun setOptArray(optArray: Array<String>?): TestInterfaceBuilder = apply { this.optArray = optArray }

  @SinceKotlin("99999999.9")
  public fun setOptBoolean(optBoolean: Boolean?): TestInterfaceBuilder = apply { this.optBoolean = optBoolean }

  @SinceKotlin("99999999.9")
  public fun setOptByte(optByte: Byte?): TestInterfaceBuilder = apply { this.optByte = optByte }

  @SinceKotlin("99999999.9")
  public fun setOptChar(optChar: Char?): TestInterfaceBuilder = apply { this.optChar = optChar }

  @SinceKotlin("99999999.9")
  public fun setOptCharSequence(optCharSequence: CharSequence?): TestInterfaceBuilder = apply { this.optCharSequence = optCharSequence }

  @SinceKotlin("99999999.9")
  public fun setOptComplexObject(optComplexObject: ComplexObject?): TestInterfaceBuilder = apply { this.optComplexObject = optComplexObject }

  @SinceKotlin("99999999.9")
  public fun setOptDouble(optDouble: Double?): TestInterfaceBuilder = apply { this.optDouble = optDouble }

  @SinceKotlin("99999999.9")
  public fun setOptFloat(optFloat: Float?): TestInterfaceBuilder = apply { this.optFloat = optFloat }

  @SinceKotlin("99999999.9")
  public fun setOptGeneratedObject(optGeneratedObject: ModelWithDefaults?): TestInterfaceBuilder = apply { this.optGeneratedObject = optGeneratedObject }

  @SinceKotlin("99999999.9")
  public fun setOptInt(optInt: Int?): TestInterfaceBuilder = apply { this.optInt = optInt }

  @SinceKotlin("99999999.9")
  public fun setOptLambda(optLambda: (() -> Unit)?): TestInterfaceBuilder = apply { this.optLambda = optLambda }

  @SinceKotlin("99999999.9")
  public fun setOptLambdaWithAnnotation(optLambdaWithAnnotation: @Composable (() -> Unit)?): TestInterfaceBuilder = apply { this.optLambdaWithAnnotation = optLambdaWithAnnotation }

  @SinceKotlin("99999999.9")
  public fun setOptList(optList: List<String>?): TestInterfaceBuilder = apply { this.optList = optList }

  @SinceKotlin("99999999.9")
  public fun setOptLong(optLong: Long?): TestInterfaceBuilder = apply { this.optLong = optLong }

  @SinceKotlin("99999999.9")
  public fun setOptNumber(optNumber: Number?): TestInterfaceBuilder = apply { this.optNumber = optNumber }

  @SinceKotlin("99999999.9")
  public fun setOptShort(optShort: Short?): TestInterfaceBuilder = apply { this.optShort = optShort }

  @SinceKotlin("99999999.9")
  public fun setOptString(optString: String?): TestInterfaceBuilder = apply { this.optString = optString }

  @SinceKotlin("99999999.9")
  public fun setShort(short: Short): TestInterfaceBuilder = apply { this.short = short }

  @SinceKotlin("99999999.9")
  public fun setString(string: String): TestInterfaceBuilder = apply { this.string = string }

  public fun build(): TestInterface = TestInterfaceImpl(array = array, boolean = boolean, byte = byte, char = char, charSequence = charSequence, complexObject = requireNotNull(complexObject), double = double, float = float, generatedObject = requireNotNull(generatedObject), int = int, lambda = requireNotNull(lambda), lambdaWithAnnotation = requireNotNull(lambdaWithAnnotation), list = list, long = long, number = number, optArray = optArray, optBoolean = optBoolean, optByte = optByte, optChar = optChar, optCharSequence = optCharSequence, optComplexObject = optComplexObject, optDouble = optDouble, optFloat = optFloat, optGeneratedObject = optGeneratedObject, optInt = optInt, optLambda = optLambda, optLambdaWithAnnotation = optLambdaWithAnnotation, optList = optList, optLong = optLong, optNumber = optNumber, optShort = optShort, optString = optString, short = short, string = string)
}

@JvmSynthetic
public inline fun TestInterface(`init`: TestInterfaceBuilder.() -> Unit = {}): TestInterface = TestInterfaceBuilder().apply(init).build()

@JvmSynthetic
public inline fun TestInterface.copy(`init`: TestInterfaceBuilder.() -> Unit = {}): TestInterface = TestInterfaceBuilder(this).apply(init).build()
