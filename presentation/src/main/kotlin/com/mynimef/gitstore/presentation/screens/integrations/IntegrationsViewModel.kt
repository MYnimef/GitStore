package com.mynimef.gitstore.presentation.screens.integrations

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.gitstore.domain.EventListener
import com.mynimef.gitstore.domain.models.Integration
import com.mynimef.gitstore.domain.models.Event
import com.mynimef.gitstore.presentation.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64
import javax.inject.Inject

/**
 *
 */
@HiltViewModel
internal class IntegrationsViewModel @Inject constructor(
    private val eventListener: EventListener
) : ViewModel() {

    private val _integrationsStateFlow = MutableStateFlow(IntegrationsState.Loaded(
        connectedIntegrations = listOf(

        ),
        availableIntegrations = listOf(
            IntegrationsState.Loaded.AvailableIntegration(
                integration = Integration(
                    source = Integration.Source.GITHUB,
                    auth = Integration.Auth.PAT
                )
            ),
            IntegrationsState.Loaded.AvailableIntegration(
                integration = Integration(
                    source = Integration.Source.GITHUB,
                    auth = Integration.Auth.OAUTH
                )
            )
        )
    ))
    val authStateFlow = _integrationsStateFlow.asStateFlow()

    fun onAction(action: IntegrationsAction): Unit = when (action) {

        is IntegrationsAction.OnConnectedIntegrationClick -> {
        }

        is IntegrationsAction.OnAvailableIntegrationClick -> {
            when (action.type.source) {
                Integration.Source.GITHUB -> authGitHub()
                Integration.Source.GITLAB -> {}
                Integration.Source.BITBUCKET -> {}
            }
        }

    }

    private fun authGitHub() {
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

        viewModelScope.launch {
            eventListener.onEvent(Event.OpenUrl(url = authUrl))
        }
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