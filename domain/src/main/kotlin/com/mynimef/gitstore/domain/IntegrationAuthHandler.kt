package com.mynimef.gitstore.domain

interface IntegrationAuthHandler {

    suspend fun handleAuth()

}