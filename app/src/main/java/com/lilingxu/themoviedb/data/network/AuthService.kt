package com.lilingxu.themoviedb.data.network

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.lilingxu.themoviedb.data.ResultAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) {

    fun loginWithCredentialService(credential: AuthCredential) : Flow<ResultAPI<AuthResult>> {
        return flow {
            emit(ResultAPI.Loading())
            val authResult = withContext(Dispatchers.IO) {
                firebaseAuth.signInWithCredential(credential).await()
            }
            emit(ResultAPI.Success(authResult))
        }.catch {
            emit(ResultAPI.Error(it.message.toString()))

        }
    }
}