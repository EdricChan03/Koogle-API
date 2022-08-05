package io.github.edricchan03.koogle_api.data

import kotlinx.serialization.Serializable

// Class mappings based from https://github.com/googleapis/nodejs-googleapis-common/blob/main/src/schema.ts

@Serializable
data class Schemas(
    val discoveryVersion: String,
    val kind: String,
    val items: List<Schema>
)

@Serializable
data class Schema(
    val auth: SchemaAuth,
    val basePath: String,
    val baseUrl: String,
    val batchPath: String,
    val description: String,
    val discoveryVersion: String,
    val discoveryRestUrl: String,
    val documentationLink: String,
    val etag: String,
    val icons: SchemaIcons,
    val id: String,
    val kind: String,
    val methods: SchemaMethods,
    val name: String,
    val ownerDomain: String,
    val ownerName: String,
    val parameters: SchemaParameters,
    val protocol: String,
    val resources: SchemaResources,
    val revision: String,
    val rootUrl: String,
    val schemas: SchemaItems,
    val servicePath: String,
    val title: String,
    val version: String
)

@Serializable
data class SchemaIcons(
    val x16: String,
    val x32: String
)
