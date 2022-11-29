plugins {
    id("com.android.library")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        jniLibs.pickFirsts.add("lib/x86_64/libjsc.so")
        jniLibs.pickFirsts.add("lib/arm64-v8a/libjsc.so")
    }
}
