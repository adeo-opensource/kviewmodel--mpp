plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
    kotlin("native.cocoapods")
}

version = Dependencies.version

kotlin {
    cocoapods {
        summary = "KViewModel iOS Example"
        homepage = "https://github.com/adeo/kviewmodel--mpp"
        ios.deploymentTarget = "14.0"

        framework {
            transitiveExport = true
            baseName = "KViewModelShared"
            export(project(":kviewmodel"))
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":kviewmodel"))
                implementation(Dependencies.JetBrains.Coroutines.core)
            }
        }

        val uikitMain by getting {
            dependencies {
                api(project(":kviewmodel"))
            }
        }
    }
}