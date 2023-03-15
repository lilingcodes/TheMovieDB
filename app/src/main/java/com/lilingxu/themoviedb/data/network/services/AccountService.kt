package com.lilingxu.themoviedb.data.network.services

import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Account

interface AccountService {

    suspend fun getAccountDetails(sessionId: String): Resource<Account>

}