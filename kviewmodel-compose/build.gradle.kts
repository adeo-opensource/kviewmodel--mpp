plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

group = "adeo.kviewmodel-compose"
version = "0.1"

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":kviewmodel"))
            }
        }
    }
}