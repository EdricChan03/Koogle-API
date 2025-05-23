// File annotations can't be used in precompiled scripts - see
// https://github.com/gradle/gradle/issues/16154
//@file:Suppress("UnstableApiUsage")

package io.github.edricchan03.koogle_api

plugins {
    kotlin("multiplatform")
    org.jetbrains.dokka
}

group = "io.github.edricchan03.koogle-api"
version = "0.0.0-SNAPSHOT"

kotlin {
    explicitApi()

    jvmToolchain(11)

    jvm {
        testRuns["test"].executionTask {
            useJUnitPlatform()
        }
    }
}

dokka {
    val moduleDoc = file("Module.md")
    val moduleDocExists = moduleDoc.exists()
    if (!moduleDocExists) logger.warn("The expected Module.md file at $moduleDoc doesn't exist!")

    pluginsConfiguration.html {
        separateInheritedMembers = true
        homepageLink = "https://github.com/EdricChan03/Koogle-API"
    }

    dokkaSourceSets.configureEach {

        if (moduleDocExists) includes.from(moduleDoc)

        samples.from("src/samples/kotlin")

        // Link to source
        sourceLink {
            localDirectory = file("src/${name}/kotlin")
            remoteUrl("https://github.com/EdricChan03/Koogle-API/tree/main/${project.name}/src/${name}/kotlin")
        }

        externalDocumentationLinks {
            // KotlinX docs
            val kotlinxCoroutines by registering {
                url("https://kotlinlang.org/api/kotlinx.coroutines/")
            }
            val kotlinxSerialization by registering {
                url("https://kotlinlang.org/api/kotlinx.serialization/")
            }

            val kotlinxDateTime by registering {
                url("https://kotlinlang.org/api/kotlinx-datetime/")
                packageListUrl("https://kotlinlang.org/api/kotlinx-datetime/kotlinx-datetime/package-list")
            }

            // Ktor API docs
            val ktor by registering {
                url("https://api.ktor.io/ktor-client/")
                packageListUrl("https://api.ktor.io/package-list")
            }
        }
    }
}
