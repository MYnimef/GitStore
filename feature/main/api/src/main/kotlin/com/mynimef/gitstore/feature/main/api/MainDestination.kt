package com.mynimef.gitstore.feature.main.api

import com.mynimef.gitstore.core.navigation.api.Destination

/**
 *
 */
data class MainDestination(
    val subRoute: SubRoute
) : Destination {

    enum class SubRoute {
        FEATURED,
        SEARCH,
        SETTINGS
    }

    override val route = ROUTE_KEY

    override val params = mapOf<String, Any>(
        SUB_ROUTE_KEY to subRoute
    )

    companion object {
        const val ROUTE_KEY = "main_route"
        const val SUB_ROUTE_KEY = "sub_route_key"
    }

}
