package io.github.edricchan03.koogle_api.data

import kotlinx.serialization.Serializable

// Auth-related data classes

/** Data class to represent [SchemaRes.auth]. */
@Serializable
data class SchemaAuth(
    /** OAuth 2.0 authentication information. */
    val oauth2: SchemaAuthOAuth2? = null
)

/** Data class to represent additional metadata of OAuth 2.0 auth in [SchemaAuth.oauth2]. */
@Serializable
data class SchemaAuthOAuth2(
    /** Available OAuth 2.0 scopes. */
    val scopes: Map<String, SchemaAuthOAuth2Scope>? = emptyMap()
)

/** Data class to represent additional metadata of a scope in [SchemaAuthOAuth2.scopes]. */
@Serializable
data class SchemaAuthOAuth2Scope(
    /** Description of scope. */
    val description: String? = null
)
