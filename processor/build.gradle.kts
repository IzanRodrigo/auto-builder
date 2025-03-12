plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.maven.publish)
}

dependencies {
    implementation(projects.annotations)
    implementation(libs.google.ksp.processor.api)
    implementation(libs.kotlinpoet.lib)
    implementation(libs.kotlinpoet.ksp)
}
