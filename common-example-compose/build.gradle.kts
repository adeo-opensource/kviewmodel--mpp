plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
    kotlin("native.cocoapods")
}

android {
    namespace = "com.adeo.kviewmodel.example.compose"
}

kotlin {
    cocoapods {
        summary = "KViewModel Apple-Compose Example"
        homepage = "https://github.com/adeo/kviewmodel--mpp"
        ios.deploymentTarget = "14.0"
        version = "1.0"

        framework {
            transitiveExport = false
            isStatic = true
            baseName = "KViewModelCompose"

            freeCompilerArgs += listOf(
                "-linker-option", "-framework", "-linker-option", "Metal",
                "-linker-option", "-framework", "-linker-option", "CoreText",
                "-linker-option", "-framework", "-linker-option", "CoreGraphics",
                "-Xdisable-phases=VerifyBitcode"
            )
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(projects.commonExample)
                implementation(projects.kviewmodel)
                implementation(projects.kviewmodelCompose)
            }
        }
    }
}