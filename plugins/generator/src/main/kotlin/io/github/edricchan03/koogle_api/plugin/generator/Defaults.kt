package io.github.edricchan03.koogle_api.plugin.generator

import org.gradle.api.Task

object Defaults {
    val Task.defaultOutputDiscoveryDocsDir get() = project.layout.buildDirectory.dir("koogle-api-disc-docs")
    val defaultRootDiscoveryUrl = "https://www.googleapis.com/discovery/v1/apis"
    val Task.defaultRootDiscoveryDoc get() = defaultOutputDiscoveryDocsDir.map { it.file("index.json") }
    val Task.defaultOutputFileNameMapper get() = SchemaFileNameMapper { "${it.id.replace(":", "-")}.json" }
}
