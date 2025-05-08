package com.mynimef.gitstore.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.gitstore.domain.EventListener
import com.mynimef.gitstore.domain.models.Event
import com.mynimef.gitstore.domain.models.Navigation
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
                    eventListener.onEvent(Event.NavigateTo(navigation = Navigation.INTEGRATIONS))
                }
            }
        }
    }

}