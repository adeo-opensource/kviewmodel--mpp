plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
    id("maven-publish")
    id("convention.publication")
}

group = Dependencies.group
version = Dependencies.version

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(Dependencies.JetBrains.Coroutines.core)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += "-Xexplicit-api=strict"
    }
}