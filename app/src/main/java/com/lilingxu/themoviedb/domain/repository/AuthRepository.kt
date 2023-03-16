package com.lilingxu.themoviedb.domain.repository

import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Account

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    /* fun loginWithCredential(credential: AuthCredential): Flow<ResultAPI<AuthResult>>

     fun createUserWithEmailPassword(email: String, password: String): Flow<ResultAPI<Boolean>>

     fun isUsernameExist(username: String): Flow<ResultAPI<Boolean>>

     fun isEmailRegistered(email: String): Flow<ResultAPI<Boolean>>*/
    fun loginWithUsernamePassword(username: String, password: String): Flow<Resource<Boolean>>

    suspend fun getAccountSessionId(username: String): Resource<String>

    suspend fun registerWithTMDB(): Resource<String>

    suspend fun createSession(requestToken: String): Resource<String>

    suspend fun saveNewAccount(newAccount: Account): Resource<Boolean>

    suspend fun getAccountDetails(sessionId: String): Resource<Account>

    suspend fun deleteSession(sessionId: String): Resource<Boolean>


}