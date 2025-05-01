package com.mynimef.gitstore.data.github

import com.mynimef.gitstore.data.models.github.GithubContentResponse
import com.mynimef.gitstore.data.models.github.GithubSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("repos/{owner}/{repo}/contents/gitstore.json")
    suspend fun getAppInfo(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<GithubContentResponse>

    @GET("search/code")
    suspend fun searchRepository(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 30
    ): Response<GithubSearchResponse>

}