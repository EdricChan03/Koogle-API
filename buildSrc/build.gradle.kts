plugins {
    `kotlin-dsl`
}

dependencies {
    // For precompiled scripts
    implementation(libs.dokka.plugin)
    implementation(libs.kotlin.mpp.plugin)
}
