package com.lilingxu.themoviedb.data.repository

import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.data.network.AuthService
import com.lilingxu.themoviedb.data.network.services.AccountService
import com.lilingxu.themoviedb.domain.model.Account
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl /*@Inject constructor(
    //private val accountService: AccountService,


) */: AuthRepository {
    /*override fun loginWithUsernamePassword(
        username: String,
        password: String,
    ): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())

            emit(authService.loginWithUsernamePassword(username, password))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun registerWithTMDB(): Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading())

            emit(authService.createRequestToken())
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun createSession(requestToken: String): Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading())

            emit(authService.createSession(requestToken))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun saveNewUser(sessionId: String, account: Account): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())

            emit(authService.saveNewUser(sessionId, account))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun getUserDetails(sessionId: String): Flow<Resource<Account>> {
        return flow {
            emit(Resource.Loading())

            emit(authService.getAccountDetails(sessionId))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

*/
    override fun loginWithUsernamePassword(
        username: String,
        password: String,
    ): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun registerWithTMDB(): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override fun createSession(requestToken: String): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override fun saveNewUser(sessionId: String, account: Account): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun getUserDetails(sessionId: String): Flow<Resource<Account>> {
        TODO("Not yet implemented")
    }
}