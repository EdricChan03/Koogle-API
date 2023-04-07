package io.github.edricchan03.koogle_api.plugin.generator

import io.github.edricchan03.koogle_api.plugin.generator.Defaults.defaultOutputDiscoveryDocsDir
import io.github.edricchan03.koogle_api.plugin.generator.Defaults.defaultOutputFileNameMapper
import io.github.edricchan03.koogle_api.plugin.generator.Defaults.defaultRootDiscoveryDoc
import io.github.edricchan03.koogle_api.plugin.generator.data.DirectoryItem
import io.github.edricchan03.koogle_api.plugin.generator.data.DirectoryList
import io.github.edricchan03.koogle_api.plugin.generator.work.DownloadDiscovery
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.gradle.api.DefaultTask
import org.gradle.api.Transformer
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.workers.WorkerExecutor
import javax.inject.Inject

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
    abstract val rootDiscoveryDocFile: RegularFileProperty

    /** The directory to write the discovery documents to. */
    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    /** Mapping function used to generate the output file name based from the specified [DirectoryItem]. */
    @get:Input
    abstract val outputFileNameMapper: Property<SchemaFileNameMapper>

    /** Mapping function used to generate the output file name based from the specified [DirectoryItem]. */
    fun outputFileNameMapper(mapper: SchemaFileNameMapper) = outputFileNameMapper.set(mapper)

    @get:Inject
    abstract val workerExecutor: WorkerExecutor

    init {
        rootDiscoveryDocFile.convention(defaultRootDiscoveryDoc)
        outputDir.convention(defaultOutputDiscoveryDocsDir)
        outputFileNameMapper.convention(defaultOutputFileNameMapper)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @TaskAction
    fun downloadDiscoveries() {
        // Read from the root discovery doc
        val discoveries = Json.decodeFromStream<DirectoryList>(
            rootDiscoveryDocFile.get().asFile.inputStream()
        )

        val workQueue = workerExecutor.noIsolation()
        discoveries.items.forEach {
            workQueue.submit<DownloadDiscovery, _> {
                schemaUrl.set(it.discoveryRestUrl)
                schemaId.set(it.id)
                outputFile.set(
                    outputDir.get().asFile.resolve(outputFileNameMapper.get().transform(it))
                )
            }
        }
    }
}
