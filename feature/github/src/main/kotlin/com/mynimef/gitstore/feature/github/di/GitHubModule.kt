package com.mynimef.gitstore.feature.github.di

import com.mynimef.gitstore.core.events.lib.EventListener
import com.mynimef.gitstore.domain.DeeplinkHandler
import com.mynimef.gitstore.domain.IntegrationAuthHandler
import com.mynimef.gitstore.domain.models.Integration
import com.mynimef.gitstore.feature.github.deeplink.GitHubDeeplinkHandler
import com.mynimef.gitstore.feature.github.auth.GitHubOAuthHandler
import com.mynimef.gitstore.feature.github.auth.GitHubPatHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import dagger.multibindings.StringKey

@Module
@InstallIn(SingletonComponent::class)
internal class GitHubModule {

    @Provides
    @IntoSet
    fun provideGitHubOauthHandler(
        eventListener: EventListener
    ): Pair<Integration, IntegrationAuthHandler> {
        return Integration(source = Integration.Source.GITHUB, auth = Integration.Auth.OAUTH) to GitHubOAuthHandler(eventListener = eventListener)
    }

    @Provides
    @IntoSet
    fun provideGitHubPatHandler(
    ): Pair<Integration, IntegrationAuthHandler> {
        return Integration(source = Integration.Source.GITHUB, auth = Integration.Auth.PAT) to GitHubPatHandler()
    }

    @Provides
    @IntoMap
    @StringKey(GitHubDeeplinkHandler.DEEPLINK)
    fun provideGitHubDeeplinkHandler(
        eventListener: EventListener
    ): DeeplinkHandler {
        return GitHubDeeplinkHandler(eventListener = eventListener)
    }

}