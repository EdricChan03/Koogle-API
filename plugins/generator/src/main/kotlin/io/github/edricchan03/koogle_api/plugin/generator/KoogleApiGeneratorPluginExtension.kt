package io.github.edricchan03.koogle_api.plugin.generator

import io.github.edricchan03.koogle_api.plugin.generator.tasks.SchemaFileNameMapper
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import java.io.File
import java.net.URI
import java.nio.file.Path

/**
 * Configuration options for the [Koogle API generator plugin][KoogleApiGeneratorPlugin].
 */
public abstract class KoogleApiGeneratorPluginExtension {
    /**
     * The discovery URL to use.
     *
     * A file path can be used.
     * @since 0.1.0
     */
    public abstract val discoveryUrl: Property<URI>

    /**
     * The discovery URL to use.
     * @since 0.1.0
     */
    public fun discoveryUrl(url: String) {
        discoveryUrl.set(URI(url))
    }

    /**
     * The discovery URL to use, as a [Provider].
     * @since 0.1.0
     */
    @JvmName("discoveryUrlProviderString")
    public fun discoveryUrl(url: Provider<String>) {
        discoveryUrl.set(url.map { URI(it) })
    }

    /**
     * The discovery file to use.
     * @since 0.1.0
     */
    public fun discoveryUrl(file: File) {
        discoveryUrl.set(file.toURI())
    }

    /**
     * The discovery file to use, as a [Provider].
     * @since 0.1.0
     */
    @JvmName("discoveryUrlProviderFile")
    public fun discoveryUrl(file: Provider<File>) {
        discoveryUrl.set(file.map { it.toURI() })
    }

    /**
     * The discovery file to use, as a [Path].
     * @since 0.1.0
     */
    public fun discoveryUrl(path: Path) {
        discoveryUrl.set(path.toUri())
    }

    /**
     * The discovery file to use, as a [Provider] of [Path].
     * @since 0.1.0
     */
    @JvmName("discoveryUrlProviderPath")
    public fun discoveryUrl(path: Provider<Path>) {
        discoveryUrl.set(path.map { it.toUri() })
    }

    /**
     * The discovery file to use, as a [RegularFile].
     * @since 0.1.0
     */
    public fun discoveryUrl(file: RegularFile) {
        discoveryUrl.set(file.asFile.toURI())
    }

    /**
     * The discovery file to use, as a [Provider] of [RegularFile].
     * @since 0.1.0
     */
    @JvmName("discoveryUrlProviderRegularFile")
    public fun discoveryUrl(file: Provider<RegularFile>) {
        discoveryUrl.set(file.map { it.asFile.toURI() })
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
