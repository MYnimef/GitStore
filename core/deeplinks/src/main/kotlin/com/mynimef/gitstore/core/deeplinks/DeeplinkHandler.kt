package com.mynimef.gitstore.core.deeplinks

interface DeeplinkHandler {

    suspend fun handleDeeplink(deeplink: Deeplink)

}