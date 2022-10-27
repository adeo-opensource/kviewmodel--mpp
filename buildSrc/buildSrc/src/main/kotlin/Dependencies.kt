object Dependencies {

    const val version = "0.10"
    const val group = "com.adeo"

    object JetBrains {
        object Kotlin {
            // __KOTLIN_COMPOSE_VERSION__
            private const val VERSION = "1.7.10"
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
            const val testCommon = "org.jetbrains.kotlin:kotlin-test-common:$VERSION"
            const val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:$VERSION"
            const val testAnnotationsCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common:$VERSION"
        }

        object Compose {
            // __LATEST_COMPOSE_RELEASE_VERSION__
            private const val VERSION = "1.2.0"
            const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$VERSION"
        }

        object Coroutines {
            private const val VERSION = "1.6.4"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
        }
    }

    object Android {
        object Tools {
            object Build {
                const val gradlePlugin = "com.android.tools.build:gradle:7.0.4"
            }
        }
    }

    object AndroidX {
        object AppCompat {
            const val appCompat = "androidx.appcompat:appcompat:1.5.1"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.6.1"
        }
    }

    object Odyssey {
        private const val VERSION = "1.0.1"
        const val core = "io.github.alexgladkov:odyssey-core:$VERSION"
        const val compose = "io.github.alexgladkov:odyssey-compose:$VERSION"
    }
}