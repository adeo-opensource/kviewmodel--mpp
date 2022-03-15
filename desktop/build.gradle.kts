import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        commonMain {
            dependencies {

            }
        }

        named("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }

        named("jvmTest") {
            dependencies {
                implementation("junit:junit:4.12")
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "ru.leroymerlin.mpp.kviewmodel_demo.MainKt"

        nativeDistributions {
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi
            )
            packageName = "KViewModel Demo Desktop"
            packageVersion = "1.0.0"

            windows {
                menuGroup = "KViewModelDemo"
                upgradeUuid = "" // Provide to this unique UUID
            }
        }
    }
}