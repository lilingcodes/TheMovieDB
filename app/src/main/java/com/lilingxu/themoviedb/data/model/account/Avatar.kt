package com.lilingxu.themoviedb.data.model.account

import com.lilingxu.themoviedb.data.model.Tmdb

data class Avatar(
    val gravatar: Gravatar? = null,
    val tmdb: Tmdb? = null,
)