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
  override val charSequence: CharSequence,
  override val string: String,
  override val int: Int,
  override val double: Double,
  override val char: Char,
  override val short: Short,
  override val long: Long,
  @get:FloatRange(
    from = 0.0,
    to = 1.0,
    fromInclusive = true,
    toInclusive = true,
  )
  override val float: Float,
  override val byte: Byte,
  override val number: Number,
  override val boolean: Boolean,
  override val array: Array<String>,
  override val list: List<String>,
  override val complexObject: ComplexObject,
  override val generatedObject: ModelWithDefaults,
  override val lambda: () -> Unit,
  override val lambdaWithAnnotation: @Composable () -> Unit,
  override val optCharSequence: CharSequence?,
  override val optString: String?,
  override val optInt: Int?,
  override val optDouble: Double?,
  override val optChar: Char?,
  override val optShort: Short?,
  override val optLong: Long?,
  @get:FloatRange(
    from = 0.0,
    to = 1.0,
    fromInclusive = true,
    toInclusive = true,
  )
  override val optFloat: Float?,
  override val optByte: Byte?,
  override val optNumber: Number?,
  override val optBoolean: Boolean?,
  override val optArray: Array<String>?,
  override val optList: List<String>?,
  override val optComplexObject: ComplexObject?,
  override val optGeneratedObject: ModelWithDefaults?,
  override val optLambda: (() -> Unit)?,
  override val optLambdaWithAnnotation: @Composable (() -> Unit)?,
) : TestInterface {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as TestInterfaceImpl
    return this.charSequence == other.charSequence &&
        	this.string == other.string &&
        	this.int == other.int &&
        	this.double == other.double &&
        	this.char == other.char &&
        	this.short == other.short &&
        	this.long == other.long &&
        	this.float == other.float &&
        	this.byte == other.byte &&
        	this.number == other.number &&
        	this.boolean == other.boolean &&
        	this.array.contentEquals(other.array) &&
        	this.list == other.list &&
        	this.complexObject == other.complexObject &&
        	this.generatedObject == other.generatedObject &&
        	this.lambda == other.lambda &&
        	this.lambdaWithAnnotation == other.lambdaWithAnnotation &&
        	this.optCharSequence == other.optCharSequence &&
        	this.optString == other.optString &&
        	this.optInt == other.optInt &&
        	this.optDouble == other.optDouble &&
        	this.optChar == other.optChar &&
        	this.optShort == other.optShort &&
        	this.optLong == other.optLong &&
        	this.optFloat == other.optFloat &&
        	this.optByte == other.optByte &&
        	this.optNumber == other.optNumber &&
        	this.optBoolean == other.optBoolean &&
        	this.optArray.contentEquals(other.optArray) &&
        	this.optList == other.optList &&
        	this.optComplexObject == other.optComplexObject &&
        	this.optGeneratedObject == other.optGeneratedObject &&
        	this.optLambda == other.optLambda &&
        	this.optLambdaWithAnnotation == other.optLambdaWithAnnotation
  }

  override fun hashCode(): Int = 
  	hash(
  		charSequence,
  		string,
  		int,
  		double,
  		char,
  		short,
  		long,
  		float,
  		byte,
  		number,
  		boolean,
  		array,
  		list,
  		complexObject,
  		generatedObject,
  		lambda,
  		lambdaWithAnnotation,
  		optCharSequence,
  		optString,
  		optInt,
  		optDouble,
  		optChar,
  		optShort,
  		optLong,
  		optFloat,
  		optByte,
  		optNumber,
  		optBoolean,
  		optArray,
  		optList,
  		optComplexObject,
  		optGeneratedObject,
  		optLambda,
  		optLambdaWithAnnotation,
  	)

  override fun toString(): String = buildString {
  	append("TestInterface(")
  	append("charSequence=$charSequence, ")
  	append("string=$string, ")
  	append("int=$int, ")
  	append("double=$double, ")
  	append("char=$char, ")
  	append("short=$short, ")
  	append("long=$long, ")
  	append("float=$float, ")
  	append("byte=$byte, ")
  	append("number=$number, ")
  	append("boolean=$boolean, ")
  	append("array=${array.contentToString()}, ")
  	append("list=$list, ")
  	append("complexObject=$complexObject, ")
  	append("generatedObject=$generatedObject, ")
  	append("lambda=$lambda, ")
  	append("lambdaWithAnnotation=$lambdaWithAnnotation, ")
  	append("optCharSequence=$optCharSequence, ")
  	append("optString=$optString, ")
  	append("optInt=$optInt, ")
  	append("optDouble=$optDouble, ")
  	append("optChar=$optChar, ")
  	append("optShort=$optShort, ")
  	append("optLong=$optLong, ")
  	append("optFloat=$optFloat, ")
  	append("optByte=$optByte, ")
  	append("optNumber=$optNumber, ")
  	append("optBoolean=$optBoolean, ")
  	append("optArray=${optArray.contentToString()}, ")
  	append("optList=$optList, ")
  	append("optComplexObject=$optComplexObject, ")
  	append("optGeneratedObject=$optGeneratedObject, ")
  	append("optLambda=$optLambda, ")
  	append("optLambdaWithAnnotation=$optLambdaWithAnnotation")
  	append(")")
  }
}

public class TestInterfaceBuilder @PublishedApi internal constructor(
  source: TestInterface? = null,
) {
  public var charSequence: CharSequence = source?.charSequence ?: TestInterfaceDefaults.charSequence
    @JvmSynthetic
    set

  public var string: String = source?.string ?: TestInterfaceDefaults.string
    @JvmSynthetic
    set

  public var int: Int = source?.int ?: TestInterfaceDefaults.int
    @JvmSynthetic
    set

  public var double: Double = source?.double ?: TestInterfaceDefaults.double
    @JvmSynthetic
    set

  public var char: Char = source?.char ?: TestInterfaceDefaults.char
    @JvmSynthetic
    set

  public var short: Short = source?.short ?: TestInterfaceDefaults.short
    @JvmSynthetic
    set

  public var long: Long = source?.long ?: TestInterfaceDefaults.long
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

  public var byte: Byte = source?.byte ?: TestInterfaceDefaults.byte
    @JvmSynthetic
    set

  public var number: Number = source?.number ?: TestInterfaceDefaults.number
    @JvmSynthetic
    set

  public var boolean: Boolean = source?.boolean ?: TestInterfaceDefaults.boolean
    @JvmSynthetic
    set

  public var array: Array<String> = source?.array ?: TestInterfaceDefaults.array
    @JvmSynthetic
    set

  public var list: List<String> = source?.list ?: TestInterfaceDefaults.list
    @JvmSynthetic
    set

  public var complexObject: ComplexObject? = source?.complexObject
    @JvmSynthetic
    set

  public var generatedObject: ModelWithDefaults? = source?.generatedObject
    @JvmSynthetic
    set

  public var lambda: (() -> Unit)? = source?.lambda
    @JvmSynthetic
    set

  public var lambdaWithAnnotation: @Composable (() -> Unit)? = source?.lambdaWithAnnotation
    @JvmSynthetic
    set

  public var optCharSequence: CharSequence? =
      source?.optCharSequence ?: TestInterfaceDefaults.optCharSequence
    @JvmSynthetic
    set

  public var optString: String? = source?.optString ?: TestInterfaceDefaults.optString
    @JvmSynthetic
    set

  public var optInt: Int? = source?.optInt ?: TestInterfaceDefaults.optInt
    @JvmSynthetic
    set

  public var optDouble: Double? = source?.optDouble ?: TestInterfaceDefaults.optDouble
    @JvmSynthetic
    set

  public var optChar: Char? = source?.optChar ?: TestInterfaceDefaults.optChar
    @JvmSynthetic
    set

  public var optShort: Short? = source?.optShort ?: TestInterfaceDefaults.optShort
    @JvmSynthetic
    set

  public var optLong: Long? = source?.optLong ?: TestInterfaceDefaults.optLong
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

  public var optByte: Byte? = source?.optByte ?: TestInterfaceDefaults.optByte
    @JvmSynthetic
    set

  public var optNumber: Number? = source?.optNumber ?: TestInterfaceDefaults.optNumber
    @JvmSynthetic
    set

  public var optBoolean: Boolean? = source?.optBoolean ?: TestInterfaceDefaults.optBoolean
    @JvmSynthetic
    set

  public var optArray: Array<String>? = source?.optArray ?: TestInterfaceDefaults.optArray
    @JvmSynthetic
    set

  public var optList: List<String>? = source?.optList ?: TestInterfaceDefaults.optList
    @JvmSynthetic
    set

  public var optComplexObject: ComplexObject? =
      source?.optComplexObject ?: TestInterfaceDefaults.optComplexObject
    @JvmSynthetic
    set

  public var optGeneratedObject: ModelWithDefaults? =
      source?.optGeneratedObject ?: TestInterfaceDefaults.optGeneratedObject
    @JvmSynthetic
    set

  public var optLambda: (() -> Unit)? = source?.optLambda ?: TestInterfaceDefaults.optLambda
    @JvmSynthetic
    set

  public var optLambdaWithAnnotation: @Composable (() -> Unit)? =
      source?.optLambdaWithAnnotation ?: TestInterfaceDefaults.optLambdaWithAnnotation
    @JvmSynthetic
    set

  @SinceKotlin("99999999.9")
  public fun setCharSequence(charSequence: CharSequence): TestInterfaceBuilder = apply { this.charSequence = charSequence }

  @SinceKotlin("99999999.9")
  public fun setString(string: String): TestInterfaceBuilder = apply { this.string = string }

  @SinceKotlin("99999999.9")
  public fun setInt(int: Int): TestInterfaceBuilder = apply { this.int = int }

  @SinceKotlin("99999999.9")
  public fun setDouble(double: Double): TestInterfaceBuilder = apply { this.double = double }

  @SinceKotlin("99999999.9")
  public fun setChar(char: Char): TestInterfaceBuilder = apply { this.char = char }

  @SinceKotlin("99999999.9")
  public fun setShort(short: Short): TestInterfaceBuilder = apply { this.short = short }

  @SinceKotlin("99999999.9")
  public fun setLong(long: Long): TestInterfaceBuilder = apply { this.long = long }

  @SinceKotlin("99999999.9")
  public fun setFloat(float: Float): TestInterfaceBuilder = apply { this.float = float }

  @SinceKotlin("99999999.9")
  public fun setByte(byte: Byte): TestInterfaceBuilder = apply { this.byte = byte }

  @SinceKotlin("99999999.9")
  public fun setNumber(number: Number): TestInterfaceBuilder = apply { this.number = number }

  @SinceKotlin("99999999.9")
  public fun setBoolean(boolean: Boolean): TestInterfaceBuilder = apply { this.boolean = boolean }

  @SinceKotlin("99999999.9")
  public fun setArray(array: Array<String>): TestInterfaceBuilder = apply { this.array = array }

  @SinceKotlin("99999999.9")
  public fun setList(list: List<String>): TestInterfaceBuilder = apply { this.list = list }

  @SinceKotlin("99999999.9")
  public fun setComplexObject(complexObject: ComplexObject): TestInterfaceBuilder = apply { this.complexObject = complexObject }

  @SinceKotlin("99999999.9")
  public fun setGeneratedObject(generatedObject: ModelWithDefaults): TestInterfaceBuilder = apply { this.generatedObject = generatedObject }

  @SinceKotlin("99999999.9")
  public fun setLambda(lambda: () -> Unit): TestInterfaceBuilder = apply { this.lambda = lambda }

  @SinceKotlin("99999999.9")
  public fun setLambdaWithAnnotation(lambdaWithAnnotation: @Composable () -> Unit): TestInterfaceBuilder = apply { this.lambdaWithAnnotation = lambdaWithAnnotation }

  @SinceKotlin("99999999.9")
  public fun setOptCharSequence(optCharSequence: CharSequence?): TestInterfaceBuilder = apply { this.optCharSequence = optCharSequence }

  @SinceKotlin("99999999.9")
  public fun setOptString(optString: String?): TestInterfaceBuilder = apply { this.optString = optString }

  @SinceKotlin("99999999.9")
  public fun setOptInt(optInt: Int?): TestInterfaceBuilder = apply { this.optInt = optInt }

  @SinceKotlin("99999999.9")
  public fun setOptDouble(optDouble: Double?): TestInterfaceBuilder = apply { this.optDouble = optDouble }

  @SinceKotlin("99999999.9")
  public fun setOptChar(optChar: Char?): TestInterfaceBuilder = apply { this.optChar = optChar }

  @SinceKotlin("99999999.9")
  public fun setOptShort(optShort: Short?): TestInterfaceBuilder = apply { this.optShort = optShort }

  @SinceKotlin("99999999.9")
  public fun setOptLong(optLong: Long?): TestInterfaceBuilder = apply { this.optLong = optLong }

  @SinceKotlin("99999999.9")
  public fun setOptFloat(optFloat: Float?): TestInterfaceBuilder = apply { this.optFloat = optFloat }

  @SinceKotlin("99999999.9")
  public fun setOptByte(optByte: Byte?): TestInterfaceBuilder = apply { this.optByte = optByte }

  @SinceKotlin("99999999.9")
  public fun setOptNumber(optNumber: Number?): TestInterfaceBuilder = apply { this.optNumber = optNumber }

  @SinceKotlin("99999999.9")
  public fun setOptBoolean(optBoolean: Boolean?): TestInterfaceBuilder = apply { this.optBoolean = optBoolean }

  @SinceKotlin("99999999.9")
  public fun setOptArray(optArray: Array<String>?): TestInterfaceBuilder = apply { this.optArray = optArray }

  @SinceKotlin("99999999.9")
  public fun setOptList(optList: List<String>?): TestInterfaceBuilder = apply { this.optList = optList }

  @SinceKotlin("99999999.9")
  public fun setOptComplexObject(optComplexObject: ComplexObject?): TestInterfaceBuilder = apply { this.optComplexObject = optComplexObject }

  @SinceKotlin("99999999.9")
  public fun setOptGeneratedObject(optGeneratedObject: ModelWithDefaults?): TestInterfaceBuilder = apply { this.optGeneratedObject = optGeneratedObject }

  @SinceKotlin("99999999.9")
  public fun setOptLambda(optLambda: (() -> Unit)?): TestInterfaceBuilder = apply { this.optLambda = optLambda }

  @SinceKotlin("99999999.9")
  public fun setOptLambdaWithAnnotation(optLambdaWithAnnotation: @Composable (() -> Unit)?): TestInterfaceBuilder = apply { this.optLambdaWithAnnotation = optLambdaWithAnnotation }

  public fun build(): TestInterface = 
  	TestInterfaceImpl(
  		charSequence = charSequence,
  		string = string,
  		int = int,
  		double = double,
  		char = char,
  		short = short,
  		long = long,
  		float = float,
  		byte = byte,
  		number = number,
  		boolean = boolean,
  		array = array,
  		list = list,
  		complexObject = requireNotNull(complexObject),
  		generatedObject = requireNotNull(generatedObject),
  		lambda = requireNotNull(lambda),
  		lambdaWithAnnotation = requireNotNull(lambdaWithAnnotation),
  		optCharSequence = optCharSequence,
  		optString = optString,
  		optInt = optInt,
  		optDouble = optDouble,
  		optChar = optChar,
  		optShort = optShort,
  		optLong = optLong,
  		optFloat = optFloat,
  		optByte = optByte,
  		optNumber = optNumber,
  		optBoolean = optBoolean,
  		optArray = optArray,
  		optList = optList,
  		optComplexObject = optComplexObject,
  		optGeneratedObject = optGeneratedObject,
  		optLambda = optLambda,
  		optLambdaWithAnnotation = optLambdaWithAnnotation,
  	)
}

@JvmSynthetic
public inline fun TestInterface(`init`: TestInterfaceBuilder.() -> Unit = {}): TestInterface = TestInterfaceBuilder().apply(init).build()

@JvmSynthetic
public inline fun TestInterface.copy(`init`: TestInterfaceBuilder.() -> Unit = {}): TestInterface = TestInterfaceBuilder(this).apply(init).build()
