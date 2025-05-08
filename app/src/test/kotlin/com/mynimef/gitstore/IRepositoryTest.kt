package com.mynimef.gitstore

import com.google.common.truth.Truth.assertThat
import com.mynimef.gitstore.data.remote.AppNetworkImpl
import com.mynimef.gitstore.domain.models.ApiResult
import com.mynimef.gitstore.domain.models.Integration
import com.mynimef.gitstore.domain.AppNetwork
import kotlinx.coroutines.runBlocking
import org.junit.Test

/** Test for [AppNetworkImpl] */
internal class IRepositoryTest {

    private val impl: AppNetwork = AppNetworkImpl()

    @Test
    fun `success loading data from GitHub`() = runBlocking {
        val result = impl.getDescription(
            integrationType = Integration.GITHUB,
            owner = "MYnimef",
            repo = "FoodMood-Android"
        )
        assert(result is ApiResult.Success)
    }

    @Test
    fun `error loading data from GitHub`() = runBlocking {
        val result = impl.getDescription(
            integrationType = Integration.GITHUB,
            owner = "MYnimef",
            repo = "FoodMood-Ubuntu"
        )
        assert(result is ApiResult.Error)
    }

    @Test
    fun `success search in GitHub`() = runBlocking {
        val result = impl.searchRepository(
            integrationType = Integration.GITHUB,
            token = "",
            query = "FoodMood"
        )
        assertThat(result).isInstanceOf(ApiResult.Success::class.java)
    }

}