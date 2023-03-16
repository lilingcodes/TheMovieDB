package com.lilingxu.themoviedb.data.network.firebase

import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Account

interface AccountDAO {


    suspend fun saveAccount(newAccount: Account, onExist: suspend (String) -> Unit): Resource<Boolean>

    suspend fun getSessionIdByUsername(username: String): Resource<String>

}