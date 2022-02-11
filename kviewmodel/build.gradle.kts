plugins {
    id("multiplatform-setup")
    id("android-setup")
}

group = "adeo.kviewmodel"
version = "0.1"

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(Dependencies.JetBrains.Kotlin.coroutines)
            }
        }
    }
}