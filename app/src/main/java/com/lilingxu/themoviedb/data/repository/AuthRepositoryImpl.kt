package com.lilingxu.themoviedb.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.lilingxu.themoviedb.data.ResultAPI
import com.lilingxu.themoviedb.data.network.AuthService
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: AuthService,
) : AuthRepository {

    override fun loginWithCredential(credential: AuthCredential): Flow<ResultAPI<AuthResult>> {
        return firebaseAuth.loginWithCredentialService(credential)
    }

}