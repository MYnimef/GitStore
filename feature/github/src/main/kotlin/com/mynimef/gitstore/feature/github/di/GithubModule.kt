package com.mynimef.gitstore.feature.github.di

import com.mynimef.gitstore.domain.EventListener
import com.mynimef.gitstore.domain.IntegrationAuthHandler
import com.mynimef.gitstore.domain.models.Integration
import com.mynimef.gitstore.feature.github.authhandlers.GithubOauthHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal class GithubModule {

    @Provides
    @IntoSet
    fun provideGitHubOauthHandler(
        eventListener: EventListener
    ): Pair<Integration, IntegrationAuthHandler> {
        return Integration(source = Integration.Source.GITHUB, auth = Integration.Auth.OAUTH) to GithubOauthHandler(eventListener = eventListener)
    }

    @Provides
    @IntoSet
    fun provideGitHubPATHandler(
        eventListener: EventListener
    ): Pair<Integration, IntegrationAuthHandler> {
        return Integration(source = Integration.Source.GITHUB, auth = Integration.Auth.PAT) to object :
            IntegrationAuthHandler {
            override suspend fun handleAuth() {
            }
        }
    }

}