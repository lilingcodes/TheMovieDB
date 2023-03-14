package com.lilingxu.themoviedb.data.networkResult

sealed class ResultAPI<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null): ResultAPI<T>(data)
    class Success<T>(data: T?): ResultAPI<T>(data)
    class Error<T>(message: String, data: T? = null): ResultAPI<T>(data, message)
}
