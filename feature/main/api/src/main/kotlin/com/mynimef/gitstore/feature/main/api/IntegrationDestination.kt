package com.mynimef.gitstore.feature.main.api

import com.mynimef.gitstore.core.navigation.api.Destination

data object IntegrationDestination : Destination {

    override val route = ROUTE_KEY

    const val ROUTE_KEY = "integrations"

}