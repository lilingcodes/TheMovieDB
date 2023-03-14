package com.lilingxu.themoviedb.data.model

data class TokenDto(
    val success: Boolean,
    val expires_at: String,
    val request_token: String,
)