package com.lilingxu.themoviedb.data.model.authentication

data class TokenDto(
    val success: Boolean,
    val expires_at: String,
    val request_token: String,
)