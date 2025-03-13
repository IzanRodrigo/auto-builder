plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.google.ksp)
    application
}

dependencies {
    implementation(projects.annotations)
    implementation(libs.androidX.annotations)
    implementation(platform(libs.androidX.compose.bom))
    implementation(libs.androidX.compose.runtime)
    ksp(projects.processor)
}

application {
    mainClass = "app.izantech.plugin.autobuilder.sample.ModelProcessorSampleKt"
}
