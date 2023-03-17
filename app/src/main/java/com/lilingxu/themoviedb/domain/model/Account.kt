package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.account.AccountDto

data class Account(
    val accountId: Int,
    val username: String,
    var sessionId: String?,
    val avatar_path: String?,
) {
    constructor() : this(0, "", null, null)
}

fun AccountDto.toDomain() =
    Account(accountId = id, username = username, sessionId = null, avatar_path = null)


