package com.mynimef.gitstore.domain

import com.mynimef.gitstore.domain.models.Integration

interface AppStorage {

    fun storeToken(integrationType: Integration, token: String)

}