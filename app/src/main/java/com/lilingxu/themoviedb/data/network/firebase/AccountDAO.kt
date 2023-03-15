package com.lilingxu.themoviedb.data.network.firebase

import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Account

interface AccountDAO {

    suspend fun createNewAccount(sessionId:String, newAccount: Account) : Resource<Boolean>

}