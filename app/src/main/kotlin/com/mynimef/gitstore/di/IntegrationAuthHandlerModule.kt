package com.mynimef.gitstore.di

import android.net.Uri
import com.mynimef.gitstore.domain.EventListener
import com.mynimef.gitstore.domain.IntegrationAuthHandler
import com.mynimef.gitstore.domain.models.Event
import com.mynimef.gitstore.domain.models.Integration
import com.mynimef.gitstore.presentation.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64

@Module
@InstallIn(SingletonComponent::class)
internal class IntegrationAuthHandlerModule {

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

    @Provides
    fun provideIntegrationsMap(
        handlers: Set<@JvmSuppressWildcards Pair<Integration, IntegrationAuthHandler>>
    ): Map<Integration, IntegrationAuthHandler> {
        return handlers.toMap()
    }

}

// Example of how it will look
//TODO Replace
private class GithubOauthHandler(
    private val eventListener: EventListener
): IntegrationAuthHandler {

    override suspend fun handleAuth() {
        val codeVerifier = generateCodeVerifier()
        val codeChallenge = generateCodeChallenge(codeVerifier)

        val authUrl = Uri.Builder()
            .scheme("https")
            .authority("github.com")
            .appendPath("login")
            .appendPath("oauth")
            .appendPath("authorize")
            .appendQueryParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
            .appendQueryParameter("redirect_uri", "gitstore://integration/github")
            .appendQueryParameter("scope", "read:user")
            .appendQueryParameter("code_challenge", codeChallenge)
            .appendQueryParameter("code_challenge_method", "S256")
            .build()
            .toString()

        eventListener.onEvent(Event.OpenUrl(url = authUrl))
    }

    private fun generateCodeVerifier(): String {
        val code = ByteArray(32)
        SecureRandom().nextBytes(code)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(code)
    }

    private fun generateCodeChallenge(codeVerifier: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(codeVerifier.toByteArray())
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
    }

}