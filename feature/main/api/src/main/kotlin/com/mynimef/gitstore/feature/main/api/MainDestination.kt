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
        SUB_ROUTE_PARAM_KEY to subRoute
    )

    companion object {
        const val SUB_ROUTE_PARAM_KEY = "sub_route_param_key"

        const val ROUTE_KEY = "main_route"
    }

}
