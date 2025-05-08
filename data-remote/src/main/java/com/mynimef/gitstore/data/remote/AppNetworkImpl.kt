package com.mynimef.gitstore.data.remote

import com.mynimef.gitstore.data.remote.github.GitHubNetwork
import com.mynimef.gitstore.domain.AppNetwork
import com.mynimef.gitstore.domain.models.Integration

class AppNetworkImpl: AppNetwork {

    private val networkGitHub = GitHubNetwork()

    override suspend fun logIn(
        integration: Integration,
        token: String
    ) = when (integration.source) {
        Integration.Source.GITHUB -> networkGitHub.logIn(token = token)
        Integration.Source.GITLAB -> TODO()
        Integration.Source.BITBUCKET -> TODO()
    }

    override suspend fun searchRepository(
        integration: Integration,
        token: String,
        query: String
    ) = when (integration.source) {
        Integration.Source.GITHUB -> networkGitHub.searchRepository(
            token = token,
            query = query
        )
        Integration.Source.GITLAB -> TODO()
        Integration.Source.BITBUCKET -> TODO()
    }

    override suspend fun getDescription(
        integration: Integration,
        owner: String,
        repo: String
    ) = when (integration.source) {
        Integration.Source.GITHUB -> networkGitHub.getDescription(owner = owner, repo = repo)
        Integration.Source.GITLAB -> TODO()
        Integration.Source.BITBUCKET -> TODO()
    }

}