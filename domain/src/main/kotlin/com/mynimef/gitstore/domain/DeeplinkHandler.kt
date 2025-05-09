package com.mynimef.gitstore.domain

import com.mynimef.gitstore.domain.models.Deeplink
import com.mynimef.gitstore.domain.models.Integration

interface DeeplinkHandler {

    suspend fun handleDeeplink(deeplink: Deeplink)

}