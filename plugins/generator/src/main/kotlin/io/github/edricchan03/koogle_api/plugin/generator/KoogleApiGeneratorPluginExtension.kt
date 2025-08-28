package io.github.edricchan03.koogle_api.plugin.generator

import io.github.edricchan03.koogle_api.plugin.generator.tasks.SchemaFileNameMapper
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import java.net.URL

/**
 * Configuration options for the [Koogle API generator plugin][KoogleApiGeneratorPlugin].
 */
public abstract class KoogleApiGeneratorPluginExtension {
    /** The discovery URL to use. */
    public abstract val discoveryUrl: Property<URL>

    /** The discovery URL to use. */
    public fun discoveryUrl(url: String) {
        discoveryUrl.set(URL(url))
    }

    /**
     * The file to output to.
     *
     * If this value is set to `null`, it will be output to the project's build directory
     * with the file name "koogle-api-discovery.json".
     * @since 0.1.0
     */
    public abstract val rootDiscoveryDocOutputFile: RegularFileProperty

    /**
     * The directory to save all discovery documents to.
     *
     * If this value is set to `null`, it will by default be saved to the
     * project's build directory in a sub-directory "koogle-api-disc-docs".
     * @since 0.1.0
     */
    public abstract val discoveryDocsOutputDir: DirectoryProperty

    /**
     * Mapping function used to generate the output file name based from
     * the specified [DirectoryItem].
     * @since 0.1.0
     */
    public abstract val outputFileNameMapper: Property<SchemaFileNameMapper>

    /**
     * Mapping function used to generate the output file name
     * based from the specified [DirectoryItem].
     * @since 0.1.0
     */
    public fun outputFileNameMapper(mapper: SchemaFileNameMapper) {
        outputFileNameMapper.set(mapper)
    }
}
