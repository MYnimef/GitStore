package com.mynimef.gitstore.domain

import com.mynimef.gitstore.domain.models.Deeplink
import com.mynimef.gitstore.domain.models.Integration

class DeeplinkHandler(
    private val storage: AppStorage
) {

    suspend fun handleDeeplink(deeplink: Deeplink) {
        when (deeplink.host) {
            "integration" -> onIntegrationDeeplink(deeplink)
            else -> {}
        }
    }

    private suspend fun onIntegrationDeeplink(deeplink: Deeplink) {
        when (deeplink.path) {
            "/github" -> {
                deeplink.params["code"]?.let { token ->
                    storage.storeToken(
                        integrationType = Integration(
                            source = Integration.Source.GITHUB,
                            auth = Integration.Auth.PAT
                        ),
                        token = token
                    )
                }
            }
            else -> {}
        }
    }

}