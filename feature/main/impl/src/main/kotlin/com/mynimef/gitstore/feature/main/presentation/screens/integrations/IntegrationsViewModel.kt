package com.mynimef.gitstore.feature.main.presentation.screens.integrations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.gitstore.domain.EventListener
import com.mynimef.gitstore.domain.IntegrationAuthHandler
import com.mynimef.gitstore.domain.models.Event
import com.mynimef.gitstore.domain.models.Integration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 */
@HiltViewModel
internal class IntegrationsViewModel @Inject constructor(
    private val eventListener: EventListener,
    private val handlersMap: @JvmSuppressWildcards Map<Integration, IntegrationAuthHandler>
) : ViewModel() {

    private var isBlocked = false

    private val _integrationsStateFlow = MutableStateFlow(IntegrationsState.Loaded(
        connectedIntegrations = listOf(

        ),
        availableIntegrations = handlersMap.map { (integration, _) ->
            IntegrationsState.Loaded.AvailableIntegration(
                integration = Integration(
                    source = integration.source,
                    auth = integration.auth
                )
            )
        }
    ))
    val authStateFlow = _integrationsStateFlow.asStateFlow()

    fun onAction(action: IntegrationsAction) {
        if (isBlocked) return

        when (action) {

            IntegrationsAction.OnExitClick -> {
                isBlocked = true
                viewModelScope.launch {
                    eventListener.onEvent(Event.NavigateBack)
                }
            }

            is IntegrationsAction.OnConnectedIntegrationClick -> {
            }

            is IntegrationsAction.OnAvailableIntegrationClick -> {
                viewModelScope.launch {
                    handlersMap[action.type]?.handleAuth()
                }
            }

        }
    }

}