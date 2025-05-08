package com.mynimef.gitstore.presentation.screens.settings

sealed interface SettingsAction {

    data object OnIntegrationsClick : SettingsAction

}