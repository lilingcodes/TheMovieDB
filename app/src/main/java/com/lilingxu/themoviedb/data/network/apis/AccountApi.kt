package com.lilingxu.themoviedb.data.network.apis

import com.lilingxu.themoviedb.data.model.AccountDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountApi {

    @GET("account")
    suspend fun getAccountDetails(
        @Query("api_key") token: String,
        @Query("session_id") sessionId: String,
    ): Response<AccountDto>

}