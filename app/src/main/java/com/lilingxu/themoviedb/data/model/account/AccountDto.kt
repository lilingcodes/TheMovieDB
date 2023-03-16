package com.lilingxu.themoviedb.data.model.account

data class AccountDto(
    val id: Int,
    val username: String,
    val name: String,
    val avatar: Avatar,
    val include_adult: Boolean,
    val iso_3166_1: String,
    val iso_639_1: String,
)