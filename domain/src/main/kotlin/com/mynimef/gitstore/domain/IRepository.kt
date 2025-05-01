package com.mynimef.gitstore.domain

interface IRepository {

    suspend fun searchRepository(
        query: String
    ): ApiResult<List<IAppInfo>>

    suspend fun getDescription(
        source: ESource,
        owner: String,
        repo: String
    ): ApiResult<IAppInfo>

}