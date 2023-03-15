package com.lilingxu.themoviedb.data.network.apis

import com.google.gson.JsonObject
import com.lilingxu.themoviedb.data.model.LoginModel
import com.lilingxu.themoviedb.data.model.SessionDto
import com.lilingxu.themoviedb.data.model.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthenticationApi {

    @GET("authentication/token/new")
    suspend fun createRequestToken(
        @Query("api_key") token: String,
    ): Response<TokenDto>

    @POST("authentication/session/new")
    suspend fun createSession(
        @Query("api_key") token: String,
        @Body request_token: JsonObject,
    ): Response<SessionDto>

    @POST("authentication/token/validate_with_login")
    suspend fun createSessionWithLogin(
        @Query("api_key") token: String,
        @Body body: LoginModel,
    ): Response<TokenDto>

}