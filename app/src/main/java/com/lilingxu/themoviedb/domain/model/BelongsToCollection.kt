package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.details.BelongsToCollectionDto

data class BelongsToCollection(
    val backdrop_path: String = "",
    val id: Int = 0,
    val name: String = "",
    val poster_path: String = "",
)

fun BelongsToCollectionDto.toDomain(): BelongsToCollection = BelongsToCollection(
    backdrop_path = backdrop_path.orEmpty(),
    id = id ?: 0,
    name = name.orEmpty(),
    poster_path = poster_path.orEmpty(),
)