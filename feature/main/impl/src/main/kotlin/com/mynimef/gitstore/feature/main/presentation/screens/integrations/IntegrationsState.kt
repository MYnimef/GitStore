package com.mynimef.gitstore.feature.main.presentation.screens.integrations

import com.mynimef.gitstore.domain.models.Integration

/**
 *
 */
sealed interface IntegrationsState {

    /**
     *
     */
    data class Loaded(
        val connectedIntegrations: List<ConnectedIntegration>,
        val availableIntegrations: List<AvailableIntegration>
    ) : IntegrationsState {

        data class ConnectedIntegration(
            val integration: Integration,
            val username: String,
        )

        data class AvailableIntegration(
            val integration: Integration,
        )

    }

    /**
     *
     */
    data object Loading: IntegrationsState

}