plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":kviewmodel"))
                implementation(Dependencies.JetBrains.Kotlin.coroutines)
            }
        }
    }
}