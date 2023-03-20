package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.account.AccountResponseDto

data class Account(
    val accountId: Int = 0,
    val username: String = "",
    var sessionId: String = "",
    val avatar_path: String = "",
) {

}

fun AccountResponseDto.toDomain() =
    Account(
        accountId = id ?: 0,
        username = username.orEmpty(),
        sessionId = "",
        avatar_path = avatar?.tmdb?.avatar_path.orEmpty()
    )


