package com.lilingxu.themoviedb.domain.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.lilingxu.themoviedb.data.ResultAPI

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun loginWithCredential(credential: AuthCredential): Flow<ResultAPI<AuthResult>>

    fun isValidUsername(username:String): Flow<ResultAPI<Boolean>>
}