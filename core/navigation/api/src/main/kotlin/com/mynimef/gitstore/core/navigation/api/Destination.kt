package com.mynimef.gitstore.core.navigation.api

/**
 *
 */
interface Destination {

    /**
     *
     */
    val route: String

    /**
     *
     */
    val params: Map<String, Any> get() = emptyMap()

}