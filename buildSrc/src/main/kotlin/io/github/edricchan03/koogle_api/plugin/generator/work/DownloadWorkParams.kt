package io.github.edricchan03.koogle_api.plugin.generator.work

import io.github.edricchan03.koogle_api.plugin.generator.InternalPluginApi
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.workers.WorkParameters

interface DownloadWorkParams : WorkParameters {
    /** The URL of the schema to download. */
    val schemaUrl: Property<String>

    /** The schema's ID. Currently used for additional debugging. */
    val schemaId: Property<String>

    /** The file to save the schema to. */
    val outputFile: RegularFileProperty

    /** Retrieves the [schemaUrl] via destructuring syntax. */
    @InternalPluginApi
    operator fun component1() = schemaUrl.get()

    /** Retrieves the [schemaId] via destructuring syntax. */
    @InternalPluginApi
    operator fun component2() = schemaId.get()

    /** Retrieves the [outputFile] via destructuring syntax. */
    @InternalPluginApi
    operator fun component3() = outputFile.get()
}
