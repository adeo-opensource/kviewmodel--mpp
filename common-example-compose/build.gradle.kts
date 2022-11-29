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
                implementation(projects.commonExample)
                implementation(projects.kviewmodel)
                implementation(projects.kviewmodelCompose)
            }
        }
    }
}