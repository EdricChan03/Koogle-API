package io.github.edricchan03.koogle_api.plugin.generator

import org.gradle.api.Task

object Defaults {
    val Task.defaultOutputDiscoveryDocsDir get() = project.buildDir.resolve("koogle-api-disc-docs")
    val Task.defaultRootDiscoveryDoc get() = defaultOutputDiscoveryDocsDir.resolve("index.json")
}
