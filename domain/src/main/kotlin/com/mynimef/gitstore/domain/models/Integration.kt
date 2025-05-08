package com.mynimef.gitstore.domain.models

/**
 * Represents an integration with a version control system (VCS) like GitHub, GitLab, or Bitbucket.
 * This class contains information about the source of the integration and the authentication method used.
 *
 * @property source The source of the integration (e.g., GitHub, GitLab, Bitbucket).
 * @property auth The authentication method used for the integration (e.g., Personal Access Token, OAuth).
 */
data class Integration(
    val source: Source,
    val auth: Auth,
) {

    /**
     * Enum representing the possible sources for integration.
     */
    enum class Source {
        GITHUB,
        GITLAB,
        BITBUCKET
    }

    /**
     * Enum representing the possible authentication methods for integration.
     */
    enum class Auth {
        PAT, // Personal Access Token
        OAUTH
    }

}
