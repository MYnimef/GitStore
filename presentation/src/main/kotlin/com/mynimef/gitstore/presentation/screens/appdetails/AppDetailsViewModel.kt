package com.mynimef.gitstore.presentation.screens.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.gitstore.domain.models.ApiResult
import com.mynimef.gitstore.domain.models.Integration
import com.mynimef.gitstore.domain.AppNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AppDetailsViewModel @Inject constructor(
    private val repository: AppNetwork
): ViewModel() {

    private val _appDetailsStateFlow = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val appDetailsStateFlow = _appDetailsStateFlow.asStateFlow()

    fun loadAppInfo(
        owner: String,
        repo: String
    ) {
        viewModelScope.launch {
            when (val result = repository.getDescription(
                integration = Integration(
                    source = Integration.Source.GITHUB,
                    auth = Integration.Auth.PAT
                ),
                owner = owner,
                repo = repo
            )) {
                is ApiResult.Error -> {}
                is ApiResult.Exception -> {}
                is ApiResult.Success -> _appDetailsStateFlow.value = AppDetailsState.Success(
                    iconUrl = null,
                    name = result.data.appName,
                    developer = "MYnimef",
                    details = result.data.description
                )
            }
        }
    }

    fun onAction(action: AppDetailsAction) {
        when (action) {
            AppDetailsAction.OnDownloadClick -> {}
        }
    }

}