package com.mynimef.gitstore.feature.main.api

import com.mynimef.gitstore.core.navigation.api.Destination

/**
 *
 */
data class AppDetailsDestination(
    val name: String,
    val developer: String
) : Destination {

    override val route = ROUTE_KEY

    override val params: Map<String, Any> = mapOf(
        NAME_PARAM_KEY to name,
        DEVELOPER_PARAM_KEY to developer
    )

    companion object {
        const val NAME_PARAM_KEY = "name"
        const val DEVELOPER_PARAM_KEY = "developer"

        const val ROUTE_KEY = "appdetails_route"
    }

}