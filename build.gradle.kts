import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.kotlin.jvm)
}

allprojects {
    val javaVersion = "17"

    tasks.withType<KotlinCompile> {
        compilerOptions {
            suppressWarnings = true
            jvmTarget = JvmTarget.fromTarget(javaVersion)
            freeCompilerArgs.addAll(
                "-opt-in=kotlin.contracts.ExperimentalContracts",
                "-opt-in=kotlin.time.ExperimentalTime",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xcontext-receivers",
            )
        }
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
}