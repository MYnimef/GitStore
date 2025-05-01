package com.mynimef.gitstore.data

import com.mynimef.gitstore.domain.IRepository
import com.mynimef.gitstore.data.github.GithubSource
import com.mynimef.gitstore.domain.ESource

internal class IRepositoryImpl: IRepository {

    val github = GithubSource()

    override suspend fun searchRepository(
        query: String
    ) = github.searchRepository(query = query)

    override suspend fun getDescription(
        source: ESource,
        owner: String,
        repo: String
    ) = when (source) {
        ESource.GITHUB -> github.getDescription(owner = owner, repo = repo)
    }

}