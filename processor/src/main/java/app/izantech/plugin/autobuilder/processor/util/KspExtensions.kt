@file:OptIn(KspExperimental::class)

package app.izantech.plugin.autobuilder.processor.util

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.getClassDeclarationByName
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
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
internal val KSType.isArray
    get() = starProjection().isAssignableFrom(builtIns.arrayType)

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
        builtIns.charType -> "'0'"
        builtIns.booleanType -> "false"
        builtIns.stringType -> "\"\""
        builtIns.iterableType -> "emptyList()"
        builtIns.arrayType -> "emptyArray()"
        else -> when {
            builtIns.arrayType.isAssignableFrom(projection) -> "emptyArray()"
            builtIns.iterableType.isAssignableFrom(projection) -> "emptyList()"
            projection.instanceOf<BigDecimal>() -> "BigDecimal.ZERO"
            projection.instanceOf<BigInteger>() -> "BigInteger.ZERO"
            projection.instanceOf<CharSequence>() -> "\"\""
            projection.isMarkedNullable -> "null"
            else -> null
        }
    }

context(Resolver)
internal inline fun <reified T> KSType.instanceOf() =
    getClassDeclarationByName<T>()?.asStarProjectedType()?.isAssignableFrom(this) == true

context(Resolver)
internal inline fun <reified T> KSAnnotation.instanceOf() =
    annotationType.resolve().instanceOf<T>()

inline fun <reified T : Annotation> KSAnnotated.annotationOrNull() =
    getAnnotationsByType(T::class).firstOrNull()
