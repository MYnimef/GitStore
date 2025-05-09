package com.mynimef.gitstore.feature.github.authhandlers

import android.net.Uri
import com.mynimef.gitstore.domain.EventListener
import com.mynimef.gitstore.domain.IntegrationAuthHandler
import com.mynimef.gitstore.domain.models.Event
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64

// Example of how it will look
internal class GithubOauthHandler(
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
            .appendQueryParameter("client_id", "")
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