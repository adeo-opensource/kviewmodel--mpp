plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

dependencies {
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "com.adeo.kviewmodel.demo.MainKt"

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