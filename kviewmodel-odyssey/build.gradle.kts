plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
    id("com.vanniktech.maven.publish")
}

android {
    namespace = "com.adeo.kviewmodel.odyssey"
}

kotlin {
    android {
        publishAllLibraryVariants()
    }

    sourceSets {
        commonMain {
            dependencies {
                api(projects.kviewmodel)
                implementation(libs.odyssey.core)
                implementation(libs.odyssey.compose)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += "-Xexplicit-api=strict"
    }
}