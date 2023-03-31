package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.details.ProductionCompanyDto

data class ProductionCompany(
    val id: Int = 0,
    val logo_path: String = "",
    val name: String = "",
    val origin_country: String = "",
)

fun ProductionCompanyDto.toDomain(): ProductionCompany = ProductionCompany(
    id = id ?: 0,
    logo_path = logo_path.orEmpty(),
    name = name.orEmpty(),
    origin_country = origin_country.orEmpty(),
)