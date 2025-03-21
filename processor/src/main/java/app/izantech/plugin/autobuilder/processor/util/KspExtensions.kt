@file:OptIn(KspExperimental::class)

package app.izantech.plugin.autobuilder.processor.util

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.getClassDeclarationByName
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import java.math.BigDecimal
import java.math.BigInteger

context(Resolver)
internal val KSType.isPrimitive
    get() = this == builtIns.intType ||
            this == builtIns.doubleType ||
            this == builtIns.floatType ||
            this == builtIns.longType ||
            this == builtIns.shortType ||
            this == builtIns.byteType ||
            this == builtIns.charType ||
            this == builtIns.booleanType

context(Resolver)
internal val KSType.isArray: Boolean
    get() {
        if (starProjection().isAssignableFrom(builtIns.arrayType)) {
            return true
        }

        return makeNotNullable().run {
            instanceOf<IntArray>()
                    || instanceOf<FloatArray>()
                    || instanceOf<DoubleArray>()
                    || instanceOf<LongArray>()
                    || instanceOf<ShortArray>()
                    || instanceOf<ByteArray>()
                    || instanceOf<BooleanArray>()
                    || instanceOf<CharArray>()
        }
    }


context(Resolver)
internal val KSType.isString
    get() = builtIns.stringType.isAssignableFrom(this)

context(Resolver)
internal val KSType.canBeConst
    get() = isPrimitive || isString

context(Resolver)
internal val KSType.defaultValueOrNull
    get() = when (val projection = starProjection()) {
        builtIns.anyType -> "Any()"
        builtIns.unitType -> "Unit"
        builtIns.numberType, builtIns.byteType, builtIns.shortType, builtIns.intType -> "0"
        builtIns.longType -> "0L"
        builtIns.floatType -> "0f"
        builtIns.doubleType -> "0.0"
        builtIns.charType -> "'\u0000'"
        builtIns.booleanType -> "false"
        builtIns.stringType -> "\"\""
        builtIns.arrayType -> "emptyArray()"
        else -> when {
            builtIns.arrayType.isAssignableFrom(projection) -> "emptyArray()"
            projection.instanceOf<List<*>>() -> "emptyList()"
            projection.instanceOf<Set<*>>() -> "emptySet()"
            projection.instanceOf<Map<*, *>>() -> "emptyMap()"
            projection.instanceOf<IntArray>() -> "intArrayOf()"
            projection.instanceOf<FloatArray>() -> "floatArrayOf()"
            projection.instanceOf<DoubleArray>() -> "doubleArrayOf()"
            projection.instanceOf<LongArray>() -> "longArrayOf()"
            projection.instanceOf<ShortArray>() -> "shortArrayOf()"
            projection.instanceOf<ByteArray>() -> "byteArrayOf()"
            projection.instanceOf<BooleanArray>() -> "booleanArrayOf()"
            projection.instanceOf<CharArray>() -> "charArrayOf()"
            projection.instanceOf<BigDecimal>() -> "BigDecimal.ZERO"
            projection.instanceOf<BigInteger>() -> "BigInteger.ZERO"
            projection.instanceOf("androidx.compose.ui.text.AnnotatedString") -> "AnnotatedString(\"\")"
            projection.instanceOf<CharSequence>() -> "\"\""
            projection.isMarkedNullable -> "null"
            else -> null
        }
    }

context(Resolver)
internal fun KSType.instanceOf(name: String) =
    instanceOf(getClassDeclarationByName(name))

context(Resolver)
internal inline fun <reified T> KSType.instanceOf() =
    instanceOf(getClassDeclarationByName<T>())

context(Resolver)
internal fun KSType.instanceOf(declaration: KSClassDeclaration?) =
    declaration?.asStarProjectedType()?.isAssignableFrom(this) == true

context(Resolver)
internal inline fun <reified T> KSAnnotation.instanceOf() =
    annotationType.resolve().instanceOf<T>()

inline fun <reified T : Annotation> KSAnnotated.annotationOrNull() =
    getAnnotationsByType(T::class).firstOrNull()
