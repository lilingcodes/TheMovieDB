package com.lilingxu.themoviedb.data.network

import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.data.networkResult.logExceptionError
import com.lilingxu.themoviedb.data.networkResult.logResponseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException


suspend fun <ResponseDto, Result> getApiResource(
    name: String = "",
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
            logResponseError(response, name)
        } catch (e: IOException) {
            logExceptionError(e)
        }
    }
}


inline fun <T> performFlowTemplate(crossinline call: suspend () -> Resource<T>): Flow<Resource<T>> {
    return flow {
        emit(Resource.Loading())
        emit(call())
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }
}




