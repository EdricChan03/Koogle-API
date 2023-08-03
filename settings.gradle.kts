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

includeBuild("plugins/generator") {
    name = "koogle-api-generator-plugin"
}
include(
    ":koogle-api-common",
)
