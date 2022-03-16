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
        publishAllLibraryVariants()
    }

    sourceSets {
        commonMain {
            dependencies {
                api(project(":kviewmodel"))
                implementation(Dependencies.Odyssey.core)
                implementation(Dependencies.Odyssey.compose)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += "-Xexplicit-api=strict"
    }
}