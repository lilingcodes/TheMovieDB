package com.lilingxu.themoviedb.domain.repository

import com.lilingxu.themoviedb.data.networkResult.ResultAPI
import com.lilingxu.themoviedb.domain.model.Account

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

   /* fun loginWithCredential(credential: AuthCredential): Flow<ResultAPI<AuthResult>>

    fun createUserWithEmailPassword(email: String, password: String): Flow<ResultAPI<Boolean>>

    fun isUsernameExist(username: String): Flow<ResultAPI<Boolean>>

    fun isEmailRegistered(email: String): Flow<ResultAPI<Boolean>>*/
    fun loginWithUsernamePassword(username: String, password: String): Flow<ResultAPI<Boolean>>

    fun registerWithTMDB(): Flow<ResultAPI<String>>

    fun createSession(requestToken: String): Flow<ResultAPI<String>>

    fun saveNewUser(sessionId:String, account: Account): Flow<ResultAPI<Boolean>>

    fun getUserDetails(sessionId:String): Flow<ResultAPI<Account>>



}