package com.lilingxu.themoviedb.data.model.movie.details

data class VideosDto(
    val results: List<VideoDto>? = null,
)

data class VideoDto(
    val id: String? = null,
    val iso_3166_1: String? = null,
    val iso_639_1: String? = null,
    val key: String? = null,
    val name: String? = null,
    val official: Boolean? = null,
    val published_at: String? = null,
    val site: String? = null,
    val size: Int? = null,
    val type: String? = null,
)