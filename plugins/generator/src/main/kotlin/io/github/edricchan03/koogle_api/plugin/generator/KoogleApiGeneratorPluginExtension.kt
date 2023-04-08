package io.github.edricchan03.koogle_api.plugin.generator

import io.github.edricchan03.koogle_api.plugin.generator.tasks.SchemaFileNameMapper
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import java.net.URL

abstract class KoogleApiGeneratorPluginExtension {
    /** The discovery URL to use. */
    abstract val discoveryUrl: Property<URL>

    /** The discovery URL to use. */
    fun discoveryUrl(url: String) {
        discoveryUrl.set(URL(url))
    }

    /**
     * The file to output to.
     * If this value is set to `null`, it will be output to the project's build directory
     * with the file name "koogle-api-discovery.json".
     */
    abstract val rootDiscoveryDocOutputFile: RegularFileProperty

    /**
     * The directory to save all discovery documents to.
     * If this value is set to `null`, it will by default be saved to the project's build directory
     * in a sub-directory "koogle-api-disc-docs".
     */
    abstract val discoveryDocsOutputDir: DirectoryProperty

    /** Mapping function used to generate the output file name based from the specified [DirectoryItem]. */
    abstract val outputFileNameMapper: Property<SchemaFileNameMapper?>

    /** Mapping function used to generate the output file name based from the specified [DirectoryItem]. */
    fun outputFileNameMapper(mapper: SchemaFileNameMapper) = outputFileNameMapper.set(mapper)
}
