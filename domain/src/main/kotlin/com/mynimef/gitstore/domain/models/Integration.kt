package com.mynimef.gitstore.domain.models

/**
 *
 */
data class Integration(
    val source: Source,
    val auth: Auth,
) {

    enum class Source {

        GITHUB,

        GITLAB,

        BITBUCKET

    }

    enum class Auth {

        PAT,

        OAUTH

    }

}
