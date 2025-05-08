package com.mynimef.gitstore.domain

import com.mynimef.gitstore.domain.models.ApiResult
import com.mynimef.gitstore.domain.models.IntegrationInfo
import com.mynimef.gitstore.domain.models.Integration

interface AppNetwork {

    suspend fun logIn(
        integration: Integration,
        token: String
    ): ApiResult<IntegrationInfo>

    suspend fun searchRepository(
        integration: Integration,
        token: String,
        query: String
    ): ApiResult<List<IAppInfo>>

    suspend fun getDescription(
        integration: Integration,
        owner: String,
        repo: String
    ): ApiResult<IAppInfo>

}