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

    fun registerWithTMDB(): Flow<Resource<String>>

    fun createSession(requestToken: String): Flow<Resource<String>>

    fun saveNewUser(sessionId:String, account: Account): Flow<Resource<Boolean>>

    fun getUserDetails(sessionId:String): Flow<Resource<Account>>



}