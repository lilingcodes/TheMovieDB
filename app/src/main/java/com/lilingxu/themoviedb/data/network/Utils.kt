package com.lilingxu.themoviedb.data.network

import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.data.networkResult.logExceptionError
import com.lilingxu.themoviedb.data.networkResult.logResponseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException


suspend fun <ResponseDto, Result> getApiResource(
    apiResponse: suspend () -> Response<ResponseDto>,
    onSuccess: (ResponseDto) -> Result,
): Resource<Result> {
    return withContext(Dispatchers.IO) {
        try {
            val response = apiResponse()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return@withContext Resource.Success(
                        onSuccess(body)
                    )
                }
            }
            logResponseError(response, "getMoviesResource")
        } catch (e: IOException) {
            logExceptionError(e)
        }
    }
}



