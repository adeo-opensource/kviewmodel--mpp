plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
    kotlin("native.cocoapods")
}

android {
    namespace = "com.adeo.kviewmodel.example.common"
}

kotlin {
    cocoapods {
        summary = "KViewModel iOS Example"
        homepage = "https://github.com/adeo/kviewmodel--mpp"
        ios.deploymentTarget = "14.0"
        version = "1.0"

        framework {
            transitiveExport = false
            isStatic = true
            baseName = "KViewModelShared"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.kviewmodel)
                implementation(libs.coroutines.core)
            }
        }
    }
}