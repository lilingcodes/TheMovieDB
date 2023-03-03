package com.lilingxu.themoviedb.data.network

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
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
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) {

    fun loginWithCredentialService(credential: AuthCredential): Flow<ResultAPI<AuthResult>> {
        return flow {
            emit(ResultAPI.Loading())
            val authResult = withContext(Dispatchers.IO) {
                auth.signInWithCredential(credential).await()
            }
            emit(ResultAPI.Success(authResult))
        }.catch {
            emit(ResultAPI.Error(it.message.toString()))

        }
    }

    fun createUser(email: String, password: String) {}

    fun isValidUsernameService(username: String): Flow<ResultAPI<Boolean>> {
        return flow {
            val isValid = MutableLiveData<Boolean>()
            emit(ResultAPI.Loading())
            db.collection("users")
                .document(username)
                .get()
                .addOnSuccessListener {
                    isValid.value = !it.exists()
                }
            emit(ResultAPI.Success(isValid.value))
        }.catch {
            emit(ResultAPI.Error(it.message.toString()))
        }
    }
}