package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.details.ProductionCountryDto

data class ProductionCountry(
    val iso_3166_1: String = "",
    val name: String = "",
)

fun ProductionCountryDto.toDomain(): ProductionCountry = ProductionCountry(
    iso_3166_1 = iso_3166_1.orEmpty(),
    name = name.orEmpty(),
)