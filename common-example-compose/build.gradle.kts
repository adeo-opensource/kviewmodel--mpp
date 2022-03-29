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
                implementation(project(":common-example"))
                implementation(project(":kviewmodel"))
                implementation(project(":kviewmodel-compose"))
            }
        }
    }
}