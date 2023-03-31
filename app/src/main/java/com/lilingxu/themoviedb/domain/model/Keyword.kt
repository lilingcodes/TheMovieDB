package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.details.KeywordDto

data class Keyword(
    val id: Int = 0,
    val name: String = "",
)

fun KeywordDto.toDomain(): Keyword = Keyword(
    id = id ?: 0,
    name = name.orEmpty(),
)
