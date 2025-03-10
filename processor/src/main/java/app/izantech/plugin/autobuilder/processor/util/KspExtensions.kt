@file:OptIn(KspExperimental::class)

package app.izantech.plugin.autobuilder.processor.util

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.getClassDeclarationByName
import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
import app.izantech.plugin.autobuilder.annotation.AutoBuilder

internal val KSClassDeclaration.autoBuilderAnnotation
    get() = getAnnotationsByType(AutoBuilder::class).first()

internal val KSPropertyDeclaration.autoBuilderPropertyAnnotation
    get() = getAnnotationsByType(AutoBuilder.Property::class).firstOrNull()

internal val KSType.hasAutoBuilderAnnotation
    get() = annotations.any { it.annotationType.isAnnotationPresent(AutoBuilder::class) }

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
internal val KSType.isCharSequence: Boolean
    get() {
        val charSequenceType = getClassDeclarationByName<CharSequence>()?.asStarProjectedType()
        return charSequenceType?.let(::isAssignableFrom) == true
    }

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
            builtIns.iterableType.isAssignableFrom(projection) -> "emptyList()"
            else -> null
        }
    }

internal fun KSClassDeclaration.getProperties(useInherited: Boolean) =
    when {
        useInherited -> getAllProperties().toSet()
        else -> getDeclaredProperties().toSet()
    }
