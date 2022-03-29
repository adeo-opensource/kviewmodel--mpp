object Dependencies {

    const val version = "0.5"
    const val group = "com.adeo"

    object JetBrains {
        object Kotlin {
            // __KOTLIN_COMPOSE_VERSION__
            private const val VERSION = "1.6.10"
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
            const val testCommon = "org.jetbrains.kotlin:kotlin-test-common:$VERSION"
            const val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:$VERSION"
            const val testAnnotationsCommon =
                "org.jetbrains.kotlin:kotlin-test-annotations-common:$VERSION"

            private const val coroutinesVersion = "1.6.0-native-mt"
            const val coroutines =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        }

        object Compose {
            // __LATEST_COMPOSE_RELEASE_VERSION__
            private const val VERSION = "1.2.0-alpha01-dev620"
            const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$VERSION"
        }
    }

    object Android {
        object Tools {
            object Build {
                const val gradlePlugin = "com.android.tools.build:gradle:4.1.0"
            }
        }
    }

    object AndroidX {
        object AppCompat {
            const val appCompat = "androidx.appcompat:appcompat:1.3.0-beta01"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.0-beta02"
        }
    }

    object Odyssey {
        private const val VERSION = "0.4.0"
        const val core = "io.github.alexgladkov:odyssey-core:$VERSION"
        const val compose = "io.github.alexgladkov:odyssey-compose:$VERSION"
    }
}