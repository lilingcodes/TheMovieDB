package com.lilingxu.themoviedb.data.network.apis

import com.google.gson.JsonObject
import com.lilingxu.themoviedb.data.model.authentication.DeleteDto
import com.lilingxu.themoviedb.data.model.LoginModel
import com.lilingxu.themoviedb.data.model.authentication.SessionDto
import com.lilingxu.themoviedb.data.model.authentication.TokenDto
import retrofit2.Response
import retrofit2.http.*

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

    //@DELETE("authentication/session")
    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    suspend fun deleteSession(
        @Query("api_key") token: String,
        @Body body: JsonObject,
    ): Response<DeleteDto>


}