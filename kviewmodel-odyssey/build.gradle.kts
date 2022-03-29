import org.jetbrains.compose.compose

plugins {
    // targets are explicitly declared while Odyssey doesn't support ios and js
    // id("multiplatform-compose-setup")
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")

    id("android-setup")
    id("maven-publish")
    id("convention.publication")
}

group = Dependencies.group
version = Dependencies.version

kotlin {
    jvm("desktop")

    android {
        publishLibraryVariants("release", "debug")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)

                implementation(project(":kviewmodel"))
                implementation(project(":kviewmodel-compose"))
                implementation(Dependencies.Odyssey.core)
                implementation(Dependencies.Odyssey.compose)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += "-Xexplicit-api=strict"
    }
}