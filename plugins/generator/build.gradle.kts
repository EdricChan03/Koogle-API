plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    kotlin("plugin.serialization") version embeddedKotlinVersion
}

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
