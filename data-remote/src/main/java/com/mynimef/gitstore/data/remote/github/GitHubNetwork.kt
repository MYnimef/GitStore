package com.mynimef.gitstore.data.remote.github

import com.google.gson.Gson
import com.mynimef.gitstore.data.remote.convertApiResult
import com.mynimef.gitstore.data.remote.github.models.GitHubUser
import com.mynimef.gitstore.data.remote.handleApi
import com.mynimef.gitstore.data.remote.models.AppInfo
import com.mynimef.gitstore.data.remote.github.models.GithubContentResponse
import com.mynimef.gitstore.data.remote.github.models.GithubSearchResponse
import com.mynimef.gitstore.domain.models.ApiResult
import com.mynimef.gitstore.domain.IAppInfo
import com.mynimef.gitstore.domain.models.IntegrationInfo
import com.mynimef.gitstore.domain.models.Integration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Base64

/**
 * Network client for interacting with the GitHub API.
 * Handles authentication, repository search, and app information retrieval.
 *
 * This class provides methods to:
 * - Authenticate users with GitHub using Personal Access Tokens
 * - Search for repositories containing GitStore app configurations
 * - Retrieve app information from repository files
 */
internal class GitHubNetwork {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(GithubApi::class.java)

    /**
     * Authenticates a user with GitHub using a Personal Access Token.
     *
     * @param token The GitHub Personal Access Token for authentication
     * @return [ApiResult] containing [IntegrationInfo] on success, or error information on failure
     */
    suspend fun logIn(
        token: String,
    ): ApiResult<IntegrationInfo> = convertApiResult(
        result = logInGitHub(
            token = token
        )
    ) { data ->
        ApiResult.Success(data = IntegrationInfo(
            type = Integration(
                source = Integration.Source.GITHUB,
                auth = Integration.Auth.PAT
            ),
            id = data.id.toLong(),
            username = data.login
        ))
    }

    /**
     * Searches for repositories containing GitStore app configurations.
     *
     * @param token The GitHub Personal Access Token for authentication
     * @param query The search query to find repositories
     * @return [ApiResult] containing a list of [IAppInfo] on success, or error information on failure
     */
    suspend fun searchRepository(
        token: String,
        query: String
    ): ApiResult<List<IAppInfo>> = convertApiResult(
        result = searchRepositoryGitHub(
            token = "token $token",
            query = "\"app_name\": \"$query\" in:file filename:gitstore.json"
        )
    ) { data ->
        ApiResult.Success(data = data.items.mapNotNull {
            when (val result = getDescriptionGitHub(
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

    /**
     * Retrieves app information from a specific repository.
     *
     * @param owner The repository owner's username
     * @param repo The repository name
     * @return [ApiResult] containing [IAppInfo] on success, or error information on failure
     */
    suspend fun getDescription(
        owner: String,
        repo: String
    ): ApiResult<IAppInfo> = convertApiResult(
        result = getDescriptionGitHub(
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

    private suspend fun logInGitHub(
        token: String
    ): ApiResult<GitHubUser> =
        handleApi { api.getUser(token = token) }

    private suspend fun searchRepositoryGitHub(
        token: String,
        query: String
    ): ApiResult<GithubSearchResponse> =
        handleApi { api.searchRepository(
            token = token,
            query = query
        ) }

    private suspend fun getDescriptionGitHub(
        owner: String,
        repo: String
    ): ApiResult<GithubContentResponse> =
        handleApi { api.getAppInfo(owner = owner, repo = repo) }

}