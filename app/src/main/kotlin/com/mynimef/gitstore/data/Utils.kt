package com.mynimef.gitstore.data

import com.mynimef.gitstore.domain.ApiResult
import retrofit2.HttpException
import retrofit2.Response

/**
 *
 */
internal inline fun <T : Any> handleApi(
    execute: () -> Response<T>
) : ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiResult.Success(body)
        } else {
            ApiResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        ApiResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        ApiResult.Exception(e)
    }
}

/**
 *
 */
internal inline fun <F : Any, T : Any> convertApiResult(
    result: ApiResult<F>,
    successConverter: (F) -> ApiResult<T>
) : ApiResult<T> = when (result) {
    is ApiResult.Error -> ApiResult.Error(code = result.code, message = result.message)
    is ApiResult.Exception -> ApiResult.Exception(e = result.e)
    is ApiResult.Success -> successConverter(result.data)
}