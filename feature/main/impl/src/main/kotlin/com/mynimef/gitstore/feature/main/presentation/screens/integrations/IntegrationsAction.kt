package com.mynimef.gitstore.feature.main.presentation.screens.integrations

import com.mynimef.gitstore.domain.models.Integration

internal sealed interface IntegrationsAction {

    data object OnExitClick : IntegrationsAction

    data class OnConnectedIntegrationClick(
        val type: Integration
    ) : IntegrationsAction

    data class OnAvailableIntegrationClick(
        val type: Integration
    ) : IntegrationsAction

}