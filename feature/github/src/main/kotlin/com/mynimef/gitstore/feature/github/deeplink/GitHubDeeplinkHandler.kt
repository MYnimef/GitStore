package com.mynimef.gitstore.feature.github.deeplink

import com.mynimef.gitstore.core.events.lib.Event
import com.mynimef.gitstore.core.events.lib.EventListener
import com.mynimef.gitstore.domain.DeeplinkHandler
import com.mynimef.gitstore.domain.models.Deeplink

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