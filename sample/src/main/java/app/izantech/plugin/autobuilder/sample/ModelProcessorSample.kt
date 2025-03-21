package app.izantech.plugin.autobuilder.sample

import androidx.compose.runtime.Composable
import app.izantech.plugin.autobuilder.sample.model.ComplexObject
import app.izantech.plugin.autobuilder.sample.model.FullModel
import app.izantech.plugin.autobuilder.sample.model.ModelWithDefaults
import app.izantech.plugin.autobuilder.sample.model.copy

fun main() {
    // Test FullModel
    val modelWithDefaults = ModelWithDefaults()
    val model = FullModel {
        string = "Hello, World!"
        int = 42
        double = 3.14
        lateinitProperty = ComplexObject(
            string = "Complex object",
            int = 123,
            double = 2.718,
        )
        generatedObject = modelWithDefaults
        lambda = { println("Lambda") }
        lambdaWithAnnotation = @Composable { println("Lambda with annotation") }
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
    println()
}
