package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.MovieDto

data class Movie(
    val id: Int,
    val title: String,
    val popularity: Double,
    val poster_path: String,
)

fun MovieDto.toDomain() = Movie(id, title, popularity, poster_path)
