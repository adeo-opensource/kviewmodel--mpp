plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

android {
    namespace = "com.adeo.kviewmodel.example.compose"
}

kotlin {
    android()

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