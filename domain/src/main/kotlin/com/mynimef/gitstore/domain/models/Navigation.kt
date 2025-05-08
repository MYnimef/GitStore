package com.mynimef.gitstore.domain.models

enum class Navigation(
    val route: String,
    val parentRoute: String? = null
) {

    FEATURED(route = "featured"),
    APP_SEARCH(route = "app_search"),
    SETTINGS(route = "settings"),
    INTEGRATIONS(route = "integrations"),
    APP_DETAILS(route = "app_details")

}