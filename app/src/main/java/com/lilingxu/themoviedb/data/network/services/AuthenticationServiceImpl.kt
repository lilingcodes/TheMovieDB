package com.lilingxu.themoviedb.data.network.services

import com.google.gson.JsonObject
import com.lilingxu.themoviedb.data.model.LoginModel
import com.lilingxu.themoviedb.data.network.ApiToken
import com.lilingxu.themoviedb.data.network.apis.AuthenticationApi
import com.lilingxu.themoviedb.data.network.getApiResource
import com.lilingxu.themoviedb.data.networkResult.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationServiceImpl @Inject constructor(
    @ApiToken private val token: String,
    private val authenticationApi: AuthenticationApi,
) : AuthenticationService {

    override suspend fun createRequestToken(): Resource<String> {
        return getApiResource(
            apiResponse = { authenticationApi.createRequestToken(token) },
            onSuccess = { tokenDto ->
                tokenDto.request_token
            }
        )
    }

    override suspend fun createSession(requestToken: String): Resource<String> {
        val requestBody = JsonObject()
        requestBody.addProperty("request_token", requestToken)

        return getApiResource(
            apiResponse = { authenticationApi.createSession(token, requestBody) },
            onSuccess = { sessionDto ->
                sessionDto.session_id
            }
        )
    }

    override suspend fun createSessionWithLogin(body: LoginModel): Resource<String> {
        return getApiResource(
            apiResponse = { authenticationApi.createSessionWithLogin(token, body) },
            onSuccess = { tokenDto ->
                tokenDto.request_token
            }
        )
    }
}