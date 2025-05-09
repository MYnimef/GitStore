package com.mynimef.gitstore.feature.github.deeplink

import com.mynimef.gitstore.domain.DeeplinkHandler
import com.mynimef.gitstore.domain.EventListener
import com.mynimef.gitstore.domain.models.Deeplink
import com.mynimef.gitstore.domain.models.Event

/**
 *
 */
internal class GitHubDeeplinkHandler(
    val eventListener: EventListener
) : DeeplinkHandler {

    override suspend fun handleDeeplink(deeplink: Deeplink) {
        eventListener.onEvent(event = Event.NavigateBack)
    }

    companion object {
        const val DEEPLINK = "integration/github"
    }

}