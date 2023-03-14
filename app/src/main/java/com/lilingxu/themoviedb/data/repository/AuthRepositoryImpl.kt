package com.lilingxu.themoviedb.data.repository

import com.lilingxu.themoviedb.data.networkResult.ResultAPI
import com.lilingxu.themoviedb.data.network.AuthService
import com.lilingxu.themoviedb.domain.model.Account
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
) : AuthRepository {
    /* override fun createUserWithEmailPassword(
         email: String,
         password: String,
     ): Flow<ResultAPI<Boolean>> {


         return authService.createUserWithEmailPassword(email, password)
     }

   override fun loginWithCredential(credential: AuthCredential): Flow<ResultAPI<AuthResult>> {
         return authService.signInWithCredential(credential)
     }


     override fun isUsernameExist(username: String): Flow<ResultAPI<Boolean>> {
         return authService.isUsernameExist(username)
     }

     override fun isEmailRegistered(email: String): Flow<ResultAPI<Boolean>> {
         return authService.isEmailRegistered(email)
     }*/

    override fun loginWithUsernamePassword(
        username: String,
        password: String,
    ): Flow<ResultAPI<Boolean>> {
        return flow {
            emit(ResultAPI.Loading())

            emit(authService.loginWithUsernamePassword(username, password))
        }.catch {
            emit(ResultAPI.Error(it.message.toString()))
        }
    }

    override fun registerWithTMDB(): Flow<ResultAPI<String>> {
        return flow {
            emit(ResultAPI.Loading())

            emit(authService.createRequestToken())
        }.catch {
            emit(ResultAPI.Error(it.message.toString()))
        }
    }

    override fun createSession(requestToken: String): Flow<ResultAPI<String>> {
        return flow {
            emit(ResultAPI.Loading())

            emit(authService.createSession(requestToken))
        }.catch {
            emit(ResultAPI.Error(it.message.toString()))
        }
    }

    override fun saveNewUser(sessionId: String, account: Account): Flow<ResultAPI<Boolean>> {
        return flow {
            emit(ResultAPI.Loading())

            emit(authService.saveNewUser(sessionId, account))
        }.catch {
            emit(ResultAPI.Error(it.message.toString()))
        }
    }

    override fun getUserDetails(sessionId: String): Flow<ResultAPI<Account>> {
        return flow {
            emit(ResultAPI.Loading())

            emit(authService.getAccountDetails(sessionId))
        }.catch {
            emit(ResultAPI.Error(it.message.toString()))
        }
    }


}