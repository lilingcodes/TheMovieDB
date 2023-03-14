package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.AccountDto

class Account(
    val username: String = "",
    val avatar_path: String = "",
)

fun AccountDto.toDomain() = Account(username, avatar.tmdb.avatar_path)
