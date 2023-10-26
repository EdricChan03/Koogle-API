rootProject.name = "koogle-api"


pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
//    includeBuild("plugins/generator")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

// Enables type-safe project dependencies - see
// https://docs.gradle.org/current/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

includeBuild("plugins/generator") {
    name = "koogle-api-generator-plugin"
}
include(
    ":koogle-api-common",
)
