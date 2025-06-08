package com.mynimef.gitstore.feature.main.presentation.screens.appsettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.gitstore.core.events.Event
import com.mynimef.gitstore.core.events.EventListener
import com.mynimef.gitstore.feature.main.api.IntegrationDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    private val eventListener: EventListener
) : ViewModel() {

    fun onAction(action: SettingsAction) {
        when (action) {
            SettingsAction.OnIntegrationsClick -> {
                viewModelScope.launch {
                    eventListener.onEvent(Event.NavigateTo(navigation = IntegrationDestination))
                }
            }
        }
    }

}