package com.mynimef.gitstore.feature.github.auth

import android.net.Uri
import com.mynimef.gitstore.core.events.lib.Event
import com.mynimef.gitstore.core.events.lib.EventListener
import com.mynimef.gitstore.domain.IntegrationAuthHandler
import com.mynimef.gitstore.feature.github.BuildConfig
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64

/**
 *
 */
internal class GitHubOAuthHandler(
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