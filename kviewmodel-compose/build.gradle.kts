plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
    id("maven-publish")
    id("artifactory.publication")
}

group = "adeo.kviewmodel-compose"
version = Dependencies.version

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":kviewmodel"))
            }
        }
    }
}