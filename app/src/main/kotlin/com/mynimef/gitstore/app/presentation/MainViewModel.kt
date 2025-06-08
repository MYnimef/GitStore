package com.mynimef.gitstore.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.gitstore.domain.models.Event
import com.mynimef.gitstore.domain.models.Navigation
import com.mynimef.gitstore.app.presentation.di.EventFlowAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val eventFlowAdapter: EventFlowAdapter
): ViewModel() {

    val eventFlow = eventFlowAdapter.events

    fun navigate(route: Navigation) {
        viewModelScope.launch {
            eventFlowAdapter.onEvent(Event.NavigateTo(navigation = route))
        }
    }

}