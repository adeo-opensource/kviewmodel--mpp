import org.jetbrains.kotlin.js.translate.context.Namer.kotlin

plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("maven-publish")
    id("convention.publication")
}

group = Dependencies.group
version = Dependencies.version

kotlin {
    android {
        publishAllLibraryVariants()
    }

    sourceSets {
        androidMain {
            dependencies {
                implementation(Dependencies.JetBrains.Coroutines.core)
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += "-Xexplicit-api=strict"
    }
}