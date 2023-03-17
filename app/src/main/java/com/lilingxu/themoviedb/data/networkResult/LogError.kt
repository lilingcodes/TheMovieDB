package com.lilingxu.themoviedb.data.networkResult

import android.util.Log
import com.google.gson.Gson
import com.lilingxu.themoviedb.data.model.StatusCodeDto
import retrofit2.Response
import java.io.IOException

fun <T, Y> logResponseError(response: Response<T>, name: String = ""): Resource<Y> {
    val status = getStatusCode(response)
    val message = status.status_message
    Log.e("remoteResponse", "Error ${status.status_code} in $name: $message ")
    return Resource.Error(message)
}

fun <T> logExceptionError(e: IOException, name: String = ""): Resource<T> {
    val message = e.message ?: e.toString()
    Log.e("remoteException", "$message in $name")
    return Resource.Error(message)
}

fun <T> getStatusCode(response: Response<T>) : StatusCodeDto{
    val gson = Gson()
    val errorBodyString = response.errorBody()?.string()
    return gson.fromJson(errorBodyString, StatusCodeDto::class.java)
}

