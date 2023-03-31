package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.MovieDto
import java.util.*

data class Movie(
    val id: Int = 0,
    val adult: Boolean = false,
    val title: String = "",
    val poster_path: String = "",
    val backdrop_path: String = "",
    val overview: String = "",
    val release_date: Date = Date(),
    val popularity: Double = 0.0,
    val vote_count: Int = 0,
    val vote_average: Double = 0.0,
)

fun MovieDto.toDomain() =
    Movie(
        id = id ?: 0,
        adult = adult ?: false,
        title = title.orEmpty(),
        poster_path = poster_path.orEmpty(),
        backdrop_path = backdrop_path.orEmpty(),
        overview = overview.orEmpty(),
        release_date = dateFormatter.parseOrNull(release_date) ?: Date(),
        popularity = popularity ?: 0.0,
        vote_count = vote_count ?: 0,
        vote_average = vote_average ?: 0.0,
    )

