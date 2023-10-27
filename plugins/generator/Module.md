# Module generator

[Plugin][io.github.edricchan03.koogle_api.plugin.generator.KoogleApiGeneratorPlugin] that
generates mappings for Google APIs.

## Usage

The plugin can be included via the
[plugins DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block):

```kt
plugins {
    id("io.github.edricchan03.koogle-api.generator") version "<x.y.z>"
}
```

## Configuring the plugin

The plugin can be configured via the `koogleApiGenerator` top-level accessor in
modules that use the generator as a plugin.

For more information on what properties are supported,
see [`KoogleApiGeneratorPluginExtension`][io.github.edricchan03.koogle_api.plugin.generator.KoogleApiGeneratorPluginExtension].

Their default values are defined in [`Defaults`][io.github.edricchan03.koogle_api.plugin.generator.Defaults].

### Example

```kt
import io.github.edricchan03.koogle_api.plugin.generator.tasks.SchemaFileNameMapper
import java.net.URI

plugins {
    id("io.github.edricchan03.koogle-api.generator") version "<x.y.z>"
}

koogleApiGenerator {
    // The discovery URL to use.
    // This property can also be set via the discoveryUrl() method, which takes
    // a String.
    discoveryUrl = URI("https://example.com")
    discoveryUrl("https://example.com")
    // Alternatively, a file/path can be used.
    discoveryUrl(project.layout.buildDirectory.file("example.json"))

    // The file to save the downloaded root discovery URL to.
    // If not specified, this defaults to <project>/build/koogle-api-disc-docs/index.json
    rootDiscoveryDocOutputFile = project.layout.buildDirectory.file("docs/discovery.json")

    // The directory to save all discovery URLs to.
    // If not specified, this defaults to <projects>/build/koogle-api-disc-docs
    discoveryDocsOutputDir = project.layout.buildDirectory.dir("docs")

    // Mapping function used to generate the output file name based from the specified
    // DirectoryItem.
    // If not specified, this defaults to
    // `SchemaFileNameMapper { "${it.id.replace(":", "-")}.json" }`
    // This property can also be set via the outputFileNameMapper() method, which takes
    // the same argument but allows for trailing lambda syntax.
    outputFileNameMapper = SchemaFileNameMapper { "${it.id.replace(":", "-")}.json" }
    outputFileNameMapper { "${it.id.replace(":", "-")}.json" }
}
```

## Plugin tasks

The plugin exposes the following Gradle tasks:

| Name                    | Description                                                                                | Implementation                                                                                                   |
|-------------------------|--------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| `downloadRootDiscovery` | Downloads the root Google API discovery document and saves it to disk                      | [`DownloadRootDiscoveryTask`][io.github.edricchan03.koogle_api.plugin.generator.tasks.DownloadRootDiscoveryTask] |
| `downloadDiscoveries`   | Downloads all Google API discovery documents from the root document and saves them to disk | [`DownloadDiscoveriesTask`][io.github.edricchan03.koogle_api.plugin.generator.tasks.DownloadDiscoveriesTask]     |
| `generateMappings`      | Generates a Gradle project from the specified Google API discovery document                | [`GenerateMappingsTask`][io.github.edricchan03.koogle_api.plugin.generator.tasks.GenerateMappingsTask]           |

# Package io.github.edricchan03.koogle_api.plugin.generator

Global package where common code such as
[`KoogleApiGeneratorPlugin`][io.github.edricchan03.koogle_api.plugin.generator.KoogleApiGeneratorPlugin]
and
[`KoogleApiGeneratorPluginExtension`][io.github.edricchan03.koogle_api.plugin.generator.KoogleApiGeneratorPluginExtension]
can be found.

# Package io.github.edricchan03.koogle_api.plugin.generator.data

Data classes used to represent Google API discovery documents.

The main top-level class is
[`RestDescription`][io.github.edricchan03.koogle_api.plugin.generator.data.RestDescription],
used to represent a Google API discovery document.

See the [using Google Discovery API docs](https://developers.google.com/discovery/v1/using) for more
information on how to use these classes.

# Package io.github.edricchan03.koogle_api.plugin.generator.data.json

Data classes used to represent a [JSON schema](https://json-schema.org/).

See the [using Google Discovery API docs](https://developers.google.com/discovery/v1/using#resources_and_schemas)
for more information.

# Package io.github.edricchan03.koogle_api.plugin.generator.data.json.serializer

Custom [Kotlinx.serialization serializers](https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/serializers.md)
for use. Currently, the following serializers are available:

* [`JsonSchemaFormatSerializer`][io.github.edricchan03.koogle_api.plugin.generator.data.json.serializer.JsonSchemaFormatSerializer]
  for formatting a [`JsonSchemaFormat`][io.github.edricchan03.koogle_api.plugin.generator.data.json.JsonSchemaFormat]

# Package io.github.edricchan03.koogle_api.plugin.generator.tasks

Contains [Gradle tasks](https://docs.gradle.org/current/userguide/tutorial_using_tasks.html#sec:projects_and_tasks)
which can be manually used in your own code if needed.

Normally, they can be used via the top-level API generator — see the
[plugin tasks section](#plugin-tasks) for more information.

# Package io.github.edricchan03.koogle_api.plugin.generator.work

Contains classes that use [Gradle's Worker API](https://docs.gradle.org/current/userguide/worker_api.html) to
develop parallel tasks.

Mainly used by
the [DownloadDiscoveriesTask][io.github.edricchan03.koogle_api.plugin.generator.tasks.DownloadDiscoveriesTask],
where a [unit of work](https://en.wikipedia.org/wiki/Unit_of_work) is used to download each individual discovery, as
well as sort each key.
