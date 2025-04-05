plugins {
    alias(libs.plugins.kotlin.serialization) apply false
    org.jetbrains.dokka
}

dependencies {
    dokka("io.github.edricchan03.koogle-api:generator")
    dokka(projects.koogleApiCommon)
}
