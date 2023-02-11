package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.MovieDto

data class Movie(
    val id: Int,
    val original_title: String,
    val popularity: Double,
    val poster_path: String,
)

fun MovieDto.toDomain() = Movie(id, original_title, popularity, poster_path)
