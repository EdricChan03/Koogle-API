package io.github.edricchan03.koogle_api.plugin.generator

import io.github.edricchan03.koogle_api.plugin.generator.Defaults.defaultOutputDiscoveryDocsDir
import io.github.edricchan03.koogle_api.plugin.generator.Defaults.defaultRootDiscoveryDoc
import io.github.edricchan03.koogle_api.plugin.generator.data.DirectoryItem
import io.github.edricchan03.koogle_api.plugin.generator.data.DirectoryList
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.gradle.api.DefaultTask
import org.gradle.api.Transformer
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * [Transformer] used to transform a [DirectoryItem] to its
 * [file name][String] representation.
 */
typealias SchemaFileNameMapper = Transformer<String, DirectoryItem>

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

    /** Mapping function used to generate the output file name based from the specified [DirectoryItem]. */
    @get:Input
    var outputFileNameMapper: SchemaFileNameMapper = {
        "${it.id.replace(":", "-")}.json"
    }

    @OptIn(ExperimentalSerializationApi::class)
    @TaskAction
    fun downloadDiscoveries() {
        // Read from the root discovery doc
        val discoveries = Json.decodeFromStream<DirectoryList>(rootDiscoveryDocFile.inputStream())
        println(discoveries.items.size)
    }
}
