package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.details.SpokenLanguageDto

data class SpokenLanguage(
    val english_name: String = "",
    val iso_639_1: String = "",
    val name: String = "",
)

fun SpokenLanguageDto.toDomain(): SpokenLanguage = SpokenLanguage(
    english_name = english_name.orEmpty(),
    iso_639_1 = iso_639_1.orEmpty(),
    name = name.orEmpty(),
)