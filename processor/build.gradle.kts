plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.annotations)
    implementation(libs.google.ksp.processor.api)
    implementation(libs.kotlinpoet.lib)
    implementation(libs.kotlinpoet.ksp)
}
