package app.izantech.plugin.autobuilder.processor.model

import com.squareup.kotlinpoet.AnnotationSpec
import java.util.SortedSet

internal typealias ModelProperty = AutoBuilderProperty
internal typealias ModelProperties = SortedSet<ModelProperty>
internal typealias ModelAnnotations = Iterable<AnnotationSpec>
