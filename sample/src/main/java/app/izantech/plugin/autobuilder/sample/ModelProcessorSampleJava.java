package app.izantech.plugin.autobuilder.sample;

public class ModelProcessorSampleJava {
    public static void main(String[] args) {
        var model = new EqualityModelBuilder()
                .a("Test")
                .b(42)
                .build();
        System.out.println(model);
    }
}
