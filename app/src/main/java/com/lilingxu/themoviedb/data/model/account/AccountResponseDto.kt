package com.lilingxu.themoviedb.data.model.account

data class AccountResponseDto(
    val id: Int? = null,
    val username: String? = null,
    val name: String? = null,
    val avatar: Avatar? = null,
    val include_adult: Boolean? = null,
    val iso_3166_1: String? = null,
    val iso_639_1: String? = null,
)