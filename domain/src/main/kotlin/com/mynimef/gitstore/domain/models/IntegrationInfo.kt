package com.mynimef.gitstore.domain.models

data class IntegrationInfo(
    val type: Integration,
    val id: Long,
    val username: String
)