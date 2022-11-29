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
        commonMain {
            dependencies {
                implementation(libs.coroutines.core)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += "-Xexplicit-api=strict"
    }
}