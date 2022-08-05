package io.github.edricchan03.koogle_api.data

import kotlinx.serialization.Serializable

// Auth-related data classes

@Serializable
data class SchemaAuth(
    val oauth2: SchemaAuthOAuth2
)

@Serializable
data class SchemaAuthOAuth2(
    val scopes: Map<String, SchemaAuthOAuth2Scope>
)

@Serializable
data class SchemaAuthOAuth2Scope(
    val description: String
)
