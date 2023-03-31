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

data class Avatar(
    val gravatar: Gravatar? = null,
    val tmdb: Tmdb? = null,
)

data class Tmdb(
    val avatar_path: String? = null,
)

data class Gravatar(
    val hash: String? = null,
)