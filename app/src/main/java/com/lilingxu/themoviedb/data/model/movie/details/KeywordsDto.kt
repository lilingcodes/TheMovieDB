package com.lilingxu.themoviedb.data.model.movie.details

data class KeywordsDto(
    val keyword: List<KeywordDto>? = null,
)

data class KeywordDto(
    val id: Int? = null,
    val name: String? = null,
)