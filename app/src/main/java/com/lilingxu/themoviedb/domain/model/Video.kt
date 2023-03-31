package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.details.VideoDto

class Video(
    val id: String = "",
    val iso_3166_1: String = "",
    val iso_639_1: String = "",
    val key: String = "",
    val name: String = "",
    val official: Boolean = false,
    val published_at: String = "",
    val site: String = "",
    val size: Int = 0,
    val type: String = "",
)

fun VideoDto.toDomain(): Video = Video(
    id = id.orEmpty(),
    iso_3166_1 = iso_3166_1.orEmpty(),
    iso_639_1 = iso_639_1.orEmpty(),
    key = key.orEmpty(),
    name = name.orEmpty(),
    official = official ?: false,
    published_at = published_at.orEmpty(),
    site = site.orEmpty(),
    size = size ?: 0,
    type = type.orEmpty(),
)
