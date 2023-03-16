package com.lilingxu.themoviedb.data.network.services

import com.lilingxu.themoviedb.data.network.ApiToken
import com.lilingxu.themoviedb.data.network.apis.AccountApi
import com.lilingxu.themoviedb.data.network.getApiResource
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Account
import com.lilingxu.themoviedb.domain.model.toDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountServiceImpl @Inject constructor(
    @ApiToken private val token: String,
    private val accountApi: AccountApi,
) : AccountService {

    override suspend fun getAccountDetails(sessionId: String): Resource<Account> {
        return getApiResource(
            name = "getAccountDetails",
            apiResponse ={ accountApi.getAccountDetails(token, sessionId)},
            onSuccess = {accountDto->
                accountDto.toDomain()
            }
        )
    }

}