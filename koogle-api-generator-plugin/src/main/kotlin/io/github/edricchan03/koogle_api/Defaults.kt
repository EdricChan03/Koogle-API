package io.github.edricchan03.koogle_api

import org.gradle.api.Task

object Defaults {
    val Task.defaultOutputDiscoveryDocsDir get() = project.buildDir.resolve("koogle-api-disc-docs")
    val Task.defaultRootDiscoveryDoc get() = defaultOutputDiscoveryDocsDir.resolve("index.json")
}
