package com.mynimef.gitstore.data.github

import com.google.gson.Gson
import com.mynimef.gitstore.data.convertApiResult
import com.mynimef.gitstore.data.handleApi
import com.mynimef.gitstore.data.models.AppInfo
import com.mynimef.gitstore.data.models.github.GithubContentResponse
import com.mynimef.gitstore.data.models.github.GithubSearchResponse
import com.mynimef.gitstore.domain.ApiResult
import com.mynimef.gitstore.domain.IAppInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Base64

class GithubSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(GithubApi::class.java)

    suspend fun searchRepository(
        query: String
    ): ApiResult<List<IAppInfo>> = convertApiResult(
        result = searchRepositoryGithub(
            query = "\"app_name\": $query path:gitstore.json"
        )
    ) { data ->
        ApiResult.Success(data = data.items.mapNotNull {
            when (val result = getDescriptionGithub(
                owner = it.repository.full_name.substringBefore("/"),
                repo = it.repository.full_name.substringAfter("/")
            )) {
                is ApiResult.Error -> null
                is ApiResult.Exception -> null
                is ApiResult.Success -> {
                    try {
                        val decoded = String(
                            Base64.getDecoder()
                                .decode(result.data.content!!.replace("\n", "").trim())
                        )
                        val appInfo = Gson().fromJson(decoded, AppInfo::class.java)
                        appInfo
                    } catch (_: Exception) {
                        null
                    }
                }
            }
        })
    }

    suspend fun getDescription(
        owner: String,
        repo: String
    ): ApiResult<IAppInfo> = convertApiResult(
        result = getDescriptionGithub(
            owner = owner,
            repo = repo
        )
    ) { data ->
        try {
            val decoded = String(Base64.getDecoder().decode(data.content!!.replace("\n", "").trim()))
            val appInfo = Gson().fromJson(decoded, AppInfo::class.java)
            ApiResult.Success(data = appInfo)
        } catch (e: Exception) {
            ApiResult.Exception(e = e)
        }
    }

    private suspend fun searchRepositoryGithub(
        query: String
    ): ApiResult<GithubSearchResponse> =
        handleApi { api.searchRepository(query = query) }

    private suspend fun getDescriptionGithub(
        owner: String,
        repo: String
    ): ApiResult<GithubContentResponse> =
        handleApi { api.getAppInfo(owner = owner, repo = repo) }

}