import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`
import org.gradle.kotlin.dsl.signing
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    `maven-publish`
    signing
    id("com.jfrog.artifactory")
    id("android-setup")
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

afterEvaluate {
    publishing {
        publications.withType<MavenPublication> {
            artifact(javadocJar.get())

            pom {
                name.set("KViewModel Library")
                description.set("ViewModel for Multiplatform")
                url.set("https://github.com/adeo/adeo--mobile-kviewmodel--mpp")

                developers {
                    developer {
                        id.set("Leroy Merlin Russia")
                        name.set("Leroy Merlin Russia")
                        email.set("aleksey.gladkov@leroymerlin.ru")
                    }
                }
                scm {
                    url.set("https://github.com/adeo/adeo--mobile-kviewmodel--mpp")
                }

            }
        }

        artifactory {
            setContextUrl("https://art.lmru.tech/artifactory")



            publish(delegateClosureOf<org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig> {
                repository(delegateClosureOf<org.jfrog.gradle.plugin.artifactory.dsl.DoubleDelegateWrapper> {
                    val repoKey = System.getenv("ARTIFACTORY_REPOSITORY")
                        ?: "generic-local-mobile-customer-android"

                    var envUsername = System.getenv("ARTIFACTORY_USERNAME")
                    var envPassword = System.getenv("ARTIFACTORY_PASSWORD")

                    if (envUsername == null || envPassword == null) {
                        try {
                            val properties = Properties()
                            properties.load(
                                    project.rootProject.file("artifactory_deploy_credential.properties")
                                    .inputStream()
                            )

                            envUsername = properties.getProperty("artifactory_user")
                            envPassword = properties.getProperty("artifactory_password")
                        } catch (e: Exception) {
                            println("Can't make library publish because file not found: artifactory_deploy_credential.properties")
                        }
                    }

                    setProperty("repoKey", repoKey)
                    setProperty("username", envUsername)
                    setProperty("password", envPassword)
                    setProperty("maven", true)
                })

                defaults {
                    setPublishArtifacts(true)
                    publications(
                        "androidDebug", "androidRelease", "desktop", "kotlinMultiplatform"
                    )
                }
            })
        }
    }
}