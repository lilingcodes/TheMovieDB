package com.lilingxu.themoviedb.data.repository

import com.lilingxu.themoviedb.data.model.LoginModel
import com.lilingxu.themoviedb.data.network.firebase.AccountDAO
import com.lilingxu.themoviedb.data.network.performFlowTemplate
import com.lilingxu.themoviedb.data.network.services.AccountService
import com.lilingxu.themoviedb.data.network.services.AuthenticationService
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Account
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val accountDAO: AccountDAO,
    private val authService: AuthenticationService,
    private val accountService: AccountService,
) : AuthRepository {
    override fun loginWithUsernamePassword(
        username: String,
        password: String,
    ): Flow<Resource<Boolean>> {
        return performFlowTemplate {
            val tokenResult = authService.createRequestToken()
            if (tokenResult is Resource.Success) {
                val requestBody = LoginModel(username, password, tokenResult.data!!)

                val sessionResult = authService.createSessionWithLogin(requestBody)
                if (sessionResult is Resource.Success) {
                    return@performFlowTemplate Resource.Success(true)
                } else {
                    return@performFlowTemplate Resource.Error(
                        sessionResult.message ?: "Create session unexpected error"
                    )
                }
            }
            return@performFlowTemplate Resource.Error(
                tokenResult.message ?: "Request token unexpected error"
            )
        }
    }

    override suspend fun getAccountSessionId(username: String): Resource<String> {
        return accountDAO.getSessionIdByUsername(username)
    }

    override suspend fun registerWithTMDB(): Resource<String> {
        return authService.createRequestToken()
    }

    override suspend fun createSession(requestToken: String): Resource<String> {
        return authService.createSession(requestToken)
    }

    override suspend fun saveNewAccount(newAccount: Account): Resource<Boolean> {
        return accountDAO.saveAccount(
            newAccount = newAccount,
            onExist = { sessionId ->
                authService.deleteSession(sessionId)
            })
    }

    override suspend fun getAccountDetails(sessionId: String): Resource<Account> {
        return accountService.getAccountDetails(sessionId)
    }

    override suspend fun deleteSession(sessionId: String): Resource<Boolean> {
        return authService.deleteSession(sessionId)
    }


}