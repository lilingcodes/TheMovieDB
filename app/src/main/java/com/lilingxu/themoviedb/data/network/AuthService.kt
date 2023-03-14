package com.lilingxu.themoviedb.data.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.gson.JsonObject
import com.lilingxu.themoviedb.data.model.AccountDto
import com.lilingxu.themoviedb.data.model.LoginModel
import com.lilingxu.themoviedb.data.networkResult.ResultAPI
import com.lilingxu.themoviedb.data.networkResult.logExceptionError
import com.lilingxu.themoviedb.data.networkResult.logResponseError
import com.lilingxu.themoviedb.domain.model.Account
import com.lilingxu.themoviedb.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    private val usersCollection: CollectionReference,
    private val apiAuth: TheMovieApi,
    @ApiToken private val token: String,
) {

    suspend fun createRequestToken(): ResultAPI<String> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiAuth.createRequestToken(token)
                Log.e("PRUEBAS1", "creando nuevo token request")

                if (response.isSuccessful) {
                    val tokenDto = response.body()
                    if (tokenDto != null) {
                        Log.e("PRUEBAS1", "token request ${tokenDto.request_token}")
                    }
                    if (tokenDto != null) return@withContext ResultAPI.Success(tokenDto.request_token)
                }
                Log.e("PRUEBAS1", "ERROR token request")
                logResponseError(response, "createRequestToken")
            } catch (e: IOException) {
                logExceptionError(e, "createRequestToken")
            }
        }

    }

    suspend fun loginWithUsernamePassword(
        username: String,
        password: String,
    ): ResultAPI<Boolean> {
        return withContext(Dispatchers.IO) {
            when (val result = createRequestToken()) {
                is ResultAPI.Success -> {
                    val requestToken = result.data!!
                    createSessionWithLogin(username, password, requestToken)
                }
                else -> {
                    val message = result.message!!
                    ResultAPI.Error(message)
                }
            }
        }
    }


    private suspend fun createSessionWithLogin(
        username: String,
        password: String,
        requestToken: String,
    ): ResultAPI<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val body = LoginModel(username, password, requestToken)

                val response = apiAuth.createSessionWithLogin(token, body)
                if (response.isSuccessful) {
                    val tokenDto = response.body()
                    if (tokenDto != null) return@withContext ResultAPI.Success(tokenDto.success)
                }

                logResponseError(response, "createSessionWithLogin")
            } catch (e: IOException) {
                logExceptionError(e, "createSessionWithLogin")
            }
        }
    }


    suspend fun createSession(requestToken: String): ResultAPI<String> {
        return withContext(Dispatchers.IO) {
            try {
                val requestBody = JsonObject()
                requestBody.addProperty("request_token", requestToken)
                val response = apiAuth.createSession(token, requestBody)
                if (response.isSuccessful) {
                    val sessionDto = response.body()
                    Log.e("PRUEBAS", "nuevo Session con token: $requestToken")
                    if (sessionDto != null) return@withContext ResultAPI.Success(sessionDto.session_id)
                }
                logResponseError(response, "createSession con token: $requestToken")
            } catch (e: IOException) {
                logExceptionError(e, "createSession")

            }
        }
    }

    suspend fun saveNewUser(sessionId: String, newAccount: Account): ResultAPI<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val success = MutableLiveData<Boolean>()

                usersCollection
                    .document(sessionId)
                    .set(newAccount)
                    .addOnSuccessListener {
                        success.value = true
                    }
                    .addOnFailureListener {
                        success.value = false
                    }

                success.observeForever {

                }

                return@withContext ResultAPI.Error("")

            } catch (e: IOException) {
                logExceptionError(e)
            }
        }
    }

    suspend fun getAccountDetails(sessionId: String): ResultAPI<Account> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiAuth.getAccountDetails(token, sessionId)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val account = body.toDomain()
                        return@withContext ResultAPI.Success(account)
                    }
                }
                logResponseError(response)
            } catch (e: IOException) {
                logExceptionError(e)
            }
        }
    }
}