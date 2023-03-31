package com.lilingxu.themoviedb.data.model.movie

data class MovieDto(
    val id: Int? = null,
    val adult: Boolean? = null,
    val title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val backdrop_path: String? = null,
    val release_date: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    //val original_title: String? = null,
    //val video: Boolean? = null,
    //val original_language: String? = null,
    //val genre_ids: List<Int>? = null,
)