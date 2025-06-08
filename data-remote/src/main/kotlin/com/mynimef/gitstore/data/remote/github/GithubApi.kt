package com.mynimef.gitstore.data.remote.github

import com.mynimef.gitstore.data.remote.github.models.GitHubUser
import com.mynimef.gitstore.data.remote.github.models.GithubContentResponse
import com.mynimef.gitstore.data.remote.github.models.GithubSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

internal interface GithubApi {

    @GET("user")
    suspend fun getUser(
        @Header("Authorization") token: String
    ): Response<GitHubUser>

    @GET("repos/{owner}/{repo}/contents/gitstore.json")
    suspend fun getAppInfo(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<GithubContentResponse>

    @GET("search/code")
    suspend fun searchRepository(
        @Header("Authorization") token: String,
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 30
    ): Response<GithubSearchResponse>

}