import org.jetbrains.compose.compose

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm("desktop")
    android()
    js(IR) {
        browser()
        binaries.executable()
    }

    macosX64()
    macosArm64()
    iosX64("uikitX64")
    iosArm64("uikitArm64")
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }

        val macosX64Main by getting
        val macosArm64Main by getting
        val uikitX64Main by getting
        val uikitArm64Main by getting
        val iosSimulatorArm64Main by getting

        val darwinMain by creating {
            dependsOn(commonMain)
            macosX64Main.dependsOn(this)
            macosArm64Main.dependsOn(this)
            uikitX64Main.dependsOn(this)
            uikitArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}