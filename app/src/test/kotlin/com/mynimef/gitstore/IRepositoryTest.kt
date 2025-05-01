package com.mynimef.gitstore

import com.google.common.truth.Truth.assertThat
import com.mynimef.gitstore.data.IRepositoryImpl
import com.mynimef.gitstore.domain.ApiResult
import com.mynimef.gitstore.domain.ESource
import com.mynimef.gitstore.domain.IRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

/** Test for [IRepositoryImpl] */
internal class IRepositoryTest {

    private val impl: IRepository = IRepositoryImpl()

    @Test
    fun `success loading data from GitHub`() = runBlocking {
        val result = impl.getDescription(
            source = ESource.GITHUB,
            owner = "MYnimef",
            repo = "FoodMood-Android"
        )
        assert(result is ApiResult.Success)
    }

    @Test
    fun `error loading data from GitHub`() = runBlocking {
        val result = impl.getDescription(
            source = ESource.GITHUB,
            owner = "MYnimef",
            repo = "FoodMood-Ubuntu"
        )
        assert(result is ApiResult.Error)
    }

    @Test
    fun `success search in GitHub`() = runBlocking {
        val result = impl.searchRepository("FoodMood")
        assertThat(result).isInstanceOf(ApiResult.Success::class.java)
    }

}