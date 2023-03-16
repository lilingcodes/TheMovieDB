package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.MovieDto

data class Movie(
    val id: Int = 0,
    val title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val vote_count: Int = 0,
    val vote_average: Double = 0.0,
    val poster_path: String = "",
)

fun MovieDto.toDomain() =
    Movie(id, title, overview, popularity, vote_count, vote_average, poster_path)