plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
}

kotlin {
    jvm("desktop")
    android()

    js(IR) {
        browser()
    }

    macosX64()
    macosArm64()
    iosX64("uikitX64")
    iosArm64("uikitArm64")
    iosSimulatorArm64("uikitSimulatorArm64")

    sourceSets {
        val commonMain by getting

        val iosMain by creating {
            dependsOn(commonMain)
        }

        val macosMain by creating {
            dependsOn(iosMain)
        }
        val macosX64Main by getting {
            dependsOn(macosMain)
        }
        val macosArm64Main by getting {
            dependsOn(macosMain)
        }

        val uikitMain by creating {
            dependsOn(iosMain)
        }
        val uikitX64Main by getting {
            dependsOn(uikitMain)
        }
        val uikitArm64Main by getting {
            dependsOn(uikitMain)
        }
        val uikitSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}