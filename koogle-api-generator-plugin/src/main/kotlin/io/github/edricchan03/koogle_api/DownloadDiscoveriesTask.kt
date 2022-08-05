package io.github.edricchan03.koogle_api

import io.github.edricchan03.koogle_api.Defaults.defaultOutputDiscoveryDocsDir
import io.github.edricchan03.koogle_api.Defaults.defaultRootDiscoveryDoc
import io.github.edricchan03.koogle_api.data.RootSchemas
import io.github.edricchan03.koogle_api.data.Schema
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

typealias SchemaFileNameMapper = (Schema) -> String

/**
 * Task to download all Google discovery documents from the specified root discovery document
 * and writes the results to disk.
 */
abstract class DownloadDiscoveriesTask : DefaultTask() {
    /** Path to the root discovery document file. */
    @get:InputFile
    var rootDiscoveryDocFile: File = defaultRootDiscoveryDoc

    /** The directory to write the discovery documents to. */
    @get:OutputDirectory
    var outputDir: File = defaultOutputDiscoveryDocsDir

    /** Mapping function used to generate the output file name based from the specified [Schema]. */
    @get:Input
    var outputFileNameMapper: SchemaFileNameMapper = {
        "${it.id.replace(":", "-")}.json"
    }

    @OptIn(ExperimentalSerializationApi::class)
    @TaskAction
    fun downloadDiscoveries() {
        // Read from the root discovery doc
        val discoveries = Json.decodeFromStream<RootSchemas>(rootDiscoveryDocFile.inputStream())
        println(discoveries.items.size)
    }
}
