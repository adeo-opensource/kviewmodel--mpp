plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("maven-publish")
    id("artifactory.publication")
}

group = "ru.leroymerlin.mpp"
version = Dependencies.version

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(Dependencies.JetBrains.Kotlin.coroutines)
            }
        }
    }
}