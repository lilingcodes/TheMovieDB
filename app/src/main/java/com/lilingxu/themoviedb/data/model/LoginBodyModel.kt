package com.lilingxu.themoviedb.data.model

data class LoginBodyModel(
    val username: String,
    val password: String,
    val request_token: String,
)
