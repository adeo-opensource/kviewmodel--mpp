plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("com.vanniktech.maven.publish")
}

android {
    namespace = "com.adeo.kviewmodel"
}

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