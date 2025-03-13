package app.izantech.plugin.autobuilder.sample;

import kotlin.Unit;

public class ModelProcessorSampleJava {
    public static void main(String[] args) {
        // Test equality
        var eq1 = new EqualityModelBuilder()
                .setA("Test")
                .setB(42)
                .build();
        var eq2 = new EqualityModelBuilder()
                .setB(42)
                .setA("Test")
                .build();
        println(eq1);
        println(eq2);
        println("eq1 == eq1: " + eq1.equals(eq2) + "\n");

        // Test FullModel
        var modelWithDefaults = new ModelWithDefaultsBuilder().build();
        var model = new FullModelBuilder()
                .setString("Hello, World!")
                .setInt(42)
                .setDouble(3.14)
                .setComplexObject(new ComplexObject("Complex object", 123, 2.718))
                .setGeneratedObject(modelWithDefaults)
                .setLambda(() -> {
                    println("Lambda");
                    return Unit.INSTANCE;
                })
                .setLambdaWithAnnotation(() -> {
                    println("Lambda with annotation");
                    return Unit.INSTANCE;
                })
                .build();
        var sameModel = new FullModelBuilder(model).build();
        println(model);
        println("model == sameModel: " + model.equals(sameModel) + "\n");

        // Test copy method
        var modelWithDefaultsCopy = new ModelWithDefaultsBuilder(modelWithDefaults)
                .setStringWithDefault("Optional small model")
                .setIntWithDefault(123)
                .setDoubleWithDefault(2.718)
                .build();
        var copy = new FullModelBuilder(model)
                .setOptString("Optional string")
                .setOptInt(123)
                .setOptDouble(2.718)
                .setOptComplexObject(new ComplexObject("Optional complex object", 123, 2.718))
                .setOptGeneratedObject(modelWithDefaultsCopy)
                .setOptLambda(() -> {
                    println("Optional Lambda");
                    return Unit.INSTANCE;
                })
                .build();
        println(copy);
        println("model == copy: " + model.equals(copy) + "\n");
    }

    @SuppressWarnings("java:S106")
    private static void println(Object obj) {
        System.out.println(obj);
    }
}
