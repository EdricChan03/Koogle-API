@file:Suppress("UnstableApiUsage")

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    kotlin("plugin.serialization") version embeddedKotlinVersion
    alias(libs.plugins.dokka)
}

group = "io.github.edricchan03.koogle-api"
version = "0.0.0-SNAPSHOT"

gradlePlugin {
    plugins {
        create("koogleApiGeneratorPlugin") {
            id = "io.github.edricchan03.koogle-api.generator"
            displayName = "Koogle API Generator Plugin"
            description = "Plugin to auto-generate Kotlin sources from the Google API"
            implementationClass = "io.github.edricchan03.koogle_api.plugin.generator.KoogleApiGeneratorPlugin"
        }
    }
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.contentNegotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.serialization.json)
}

dokka {
    modulePath = "plugins/generator"

    dokkaSourceSets.configureEach {
        includes.from("Module.md")

        // Link to source
        sourceLink {
            localDirectory = file("src/main/kotlin")
            remoteUrl("https://github.com/EdricChan03/Koogle-API/tree/main/plugins/generator/src/main/kotlin")
        }

        externalDocumentationLinks {
            // KotlinX docs
            val kotlinxCoroutines by registering {
                url("https://kotlinlang.org/api/kotlinx.coroutines/")
            }
            val kotlinxSerialization by registering {
                url("https://kotlinlang.org/api/kotlinx.serialization/")
            }

            // Ktor API docs
            val ktor by registering {
                url("https://api.ktor.io/ktor-client/")
                packageListUrl("https://api.ktor.io/package-list")
            }

            // Gradle docs
            val gradle by registering {
                url("https://docs.gradle.org/${gradle.gradleVersion}/kotlin-dsl/")
                packageListUrl("https://docs.gradle.org/${gradle.gradleVersion}/kotlin-dsl/gradle/package-list")
            }
        }
    }
}
