package com.lilingxu.themoviedb.data.model.movie.details

import com.lilingxu.themoviedb.data.model.movie.MovieDto

data class SimilarDto(
    val page: Int? = null,
    val results: List<MovieDto>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null,
)