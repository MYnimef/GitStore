package com.mynimef.gitstore.presentation.screens.appsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.gitstore.domain.AppNetwork
import com.mynimef.gitstore.domain.models.ApiResult
import com.mynimef.gitstore.domain.models.Integration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AppSearchViewModel @Inject constructor(
    private val network: AppNetwork
) : ViewModel() {

    private val _screenStateFlow = MutableStateFlow<AppSearchState>(AppSearchState.Loading)
    val screenStateFlow = _screenStateFlow.asStateFlow()

    fun onAction(action: AppSearchAction) {
        when (action) {
            is AppSearchAction.OnSearch -> {
                viewModelScope.launch {
                    val result = network.searchRepository(
                        integration = Integration(
                            source = Integration.Source.GITHUB,
                            auth = Integration.Auth.PAT
                        ),
                        token = "",
                        query = action.query
                    )
                    when (result) {
                        is ApiResult.Error -> {}
                        is ApiResult.Exception -> {}
                        is ApiResult.Success -> {
                            _screenStateFlow.value = AppSearchState.Success(
                                query = "",
                                apps = result.data.map {
                                    AppSearchState.Success.AppInfo(
                                        iconUrl = null,
                                        name = it.appName,
                                        developer = "MYnimef"
                                    )
                                }
                            )
                        }
                    }
                }
            }
            AppSearchAction.OnDownloadApp -> {

            }
        }
    }

}