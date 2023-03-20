package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.MovieDetailsResponseDto
import java.util.*

data class MovieDetails(
    val movie: Movie = Movie(),
    val genres: List<Genre> = emptyList(),
    val runtime: Int = 0,
    val status: String = "",
    val tagline: String = "",

)

fun MovieDetailsResponseDto.toDomain() = MovieDetails(
    movie = Movie(
        id = id ?: 0,
        title = title.orEmpty(),
        poster_path = poster_path.orEmpty(),
        backdrop_path = backdrop_path.orEmpty(),
        overview = overview.orEmpty(),
        release_date = release_date?.let { dateFormatter.parse(release_date) } ?: Date(),
        popularity = popularity ?: 0.0,
        vote_count = vote_count ?: 0,
        vote_average = vote_average ?: 0.0,
    ),

    genres = genres.orEmpty(),
    runtime = runtime ?: 0,
    status = status.orEmpty(),
    tagline = tagline.orEmpty(),
)
