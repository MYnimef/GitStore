package com.mynimef.gitstore.app.presentation

import androidx.lifecycle.ViewModel
import com.mynimef.gitstore.app.EventFlowAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val eventFlowAdapter: EventFlowAdapter
): ViewModel() {

    val eventFlow = eventFlowAdapter.events

}