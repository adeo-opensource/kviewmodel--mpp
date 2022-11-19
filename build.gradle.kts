plugins {
    id("com.android.library")  apply false
    id("org.jetbrains.kotlin.android")  apply false
    `kotlin-dsl`
}

allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }
}

buildscript {
    val kotlinVersion = "1.7.10"
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}
