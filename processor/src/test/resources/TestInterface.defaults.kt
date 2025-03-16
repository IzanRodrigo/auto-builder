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
import kotlin.Short
import kotlin.SinceKotlin
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.jvm.JvmSynthetic

public object TestInterfaceDefaults : TestInterface {
  override val charSequence: CharSequence = ""

  override val string: String = ""

  override val int: Int = 0

  override val double: Double = 0.0

  override val char: Char = '0'

  override val short: Short = 0

  override val long: Long = 0L

  @get:FloatRange(
    from = 0.0,
    to = 1.0,
    fromInclusive = true,
    toInclusive = true,
  )
  override val float: Float = 0f

  override val byte: Byte = 0

  override val number: Number = 0

  override val boolean: Boolean = false

  override val array: Array<String> = emptyArray()

  override val list: List<String> = emptyList()

  @SinceKotlin("99999999.9")
  override val complexObject: ComplexObject
    @JvmSynthetic
    get() = error("Hidden.")

  @SinceKotlin("99999999.9")
  override val generatedObject: ModelWithDefaults
    @JvmSynthetic
    get() = error("Hidden.")

  @SinceKotlin("99999999.9")
  override val lambda: () -> Unit
    @JvmSynthetic
    get() = error("Hidden.")

  @SinceKotlin("99999999.9")
  override val lambdaWithAnnotation: @Composable () -> Unit
    @JvmSynthetic
    get() = error("Hidden.")

  override val optCharSequence: CharSequence? = null

  override val optString: String? = null

  override val optInt: Int? = null

  override val optDouble: Double? = null

  override val optChar: Char? = null

  override val optShort: Short? = null

  override val optLong: Long? = null

  @get:FloatRange(
    from = 0.0,
    to = 1.0,
    fromInclusive = true,
    toInclusive = true,
  )
  override val optFloat: Float? = null

  override val optByte: Byte? = null

  override val optNumber: Number? = null

  override val optBoolean: Boolean? = null

  override val optArray: Array<String>? = null

  override val optList: List<String>? = null

  override val optComplexObject: ComplexObject? = null

  override val optGeneratedObject: ModelWithDefaults? = null

  override val optLambda: (() -> Unit)? = null

  override val optLambdaWithAnnotation: @Composable (() -> Unit)? = null
}
