package com.mynimef.gitstore.feature.integration.github.deeplink

import com.mynimef.gitstore.core.deeplinks.Deeplink
import com.mynimef.gitstore.core.deeplinks.DeeplinkHandler
import com.mynimef.gitstore.core.events.Event
import com.mynimef.gitstore.core.events.EventListener

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