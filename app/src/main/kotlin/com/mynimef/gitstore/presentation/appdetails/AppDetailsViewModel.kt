package com.mynimef.gitstore.presentation.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.gitstore.data.IRepositoryImpl
import com.mynimef.gitstore.domain.ApiResult
import com.mynimef.gitstore.domain.ESource
import com.mynimef.gitstore.domain.IRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class AppDetailsViewModel(
    private val repository: IRepository = IRepositoryImpl()
): ViewModel() {

    private val _appDetailsStateFlow = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val appDetailsStateFlow = _appDetailsStateFlow.asStateFlow()

    fun loadAppInfo(
        owner: String,
        repo: String
    ) {
        viewModelScope.launch {
            when (val result = repository.getDescription(
                source = ESource.GITHUB,
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