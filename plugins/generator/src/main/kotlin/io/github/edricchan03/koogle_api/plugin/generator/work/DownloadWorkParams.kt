package io.github.edricchan03.koogle_api.plugin.generator.work

import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.workers.WorkParameters

public interface DownloadWorkParams : WorkParameters {
    /** The URL of the schema to download. */
    public val schemaUrl: Property<String>

    /** The schema's ID. Currently used for additional debugging. */
    public val schemaId: Property<String>

    /** The file to save the schema to. */
    public val outputFile: RegularFileProperty

    /** Retrieves the [schemaUrl] via destructuring syntax. */
    public operator fun component1(): String = schemaUrl.get()

    /** Retrieves the [schemaId] via destructuring syntax. */
    public operator fun component2(): String = schemaId.get()

    /** Retrieves the [outputFile] via destructuring syntax. */
    public operator fun component3(): RegularFile = outputFile.get()
}
