package com.lilingxu.themoviedb.data.model

import com.lilingxu.themoviedb.domain.model.Genre

data class GenreResponseDto(
    val genres: List<Genre>
)

