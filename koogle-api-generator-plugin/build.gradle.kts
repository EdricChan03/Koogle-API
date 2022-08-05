plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    kotlin("plugin.serialization") version embeddedKotlinVersion
}

gradlePlugin {
    plugins {
        create("koogleApiGeneratorPlugin") {
            id = "io.github.edricchan03.koogle-api.generator"
            implementationClass = "io.github.edricchan03.koogle_api.KoogleApiGeneratorPlugin"
        }
    }
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.kotlinx.serialization.json)
}
