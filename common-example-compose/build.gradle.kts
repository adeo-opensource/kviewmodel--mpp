plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(Dependencies.JetBrains.Coroutines.core)
                implementation(project(":common-example"))
                implementation(project(":kviewmodel"))
                implementation(project(":kviewmodel-compose"))
            }
        }
    }
}