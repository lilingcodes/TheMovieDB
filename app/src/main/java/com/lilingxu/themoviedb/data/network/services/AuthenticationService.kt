package com.lilingxu.themoviedb.data.network.services

import com.lilingxu.themoviedb.data.model.LoginModel
import com.lilingxu.themoviedb.data.networkResult.Resource

interface AuthenticationService {

    suspend fun createRequestToken(): Resource<String>

    suspend fun createSession(requestToken: String): Resource<String>

    suspend fun createSessionWithLogin(body: LoginModel): Resource<String>

    suspend fun deleteSession(sessionId: String): Resource<Boolean>

}