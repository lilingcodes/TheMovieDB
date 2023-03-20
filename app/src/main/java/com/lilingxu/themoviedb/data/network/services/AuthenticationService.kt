package com.lilingxu.themoviedb.data.network.services

import com.lilingxu.themoviedb.data.model.LoginBodyModel
import com.lilingxu.themoviedb.data.networkResult.Resource

interface AuthenticationService {

    suspend fun createRequestToken(): Resource<String>

    suspend fun createSession(requestToken: String): Resource<String>

    suspend fun createSessionWithLogin(body: LoginBodyModel): Resource<String>

    suspend fun deleteSession(sessionId: String): Resource<Boolean>

}