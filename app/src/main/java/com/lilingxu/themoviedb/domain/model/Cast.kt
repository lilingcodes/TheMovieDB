package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.details.CastDto

class Cast(
    val adult: Boolean = false,
    val cast_id: Int = 0,
    val character: String = "",
    val credit_id: String = "",
    val gender: Int = 0,
    val id: Int = 0,
    val known_for_department: String = "",
    val name: String = "",
    val order: Int = 0,
    val original_name: String = "",
    val popularity: Double = 0.0,
    val profile_path: String = "",
)

fun CastDto.toDomain(): Cast = Cast(
    adult = adult ?: false,
    cast_id = cast_id ?: 0,
    character = character.orEmpty(),
    credit_id = credit_id.orEmpty(),
    gender = gender ?: 0,
    id = id ?: 0,
    known_for_department = known_for_department.orEmpty(),
    name = name.orEmpty(),
    order = order ?: 0,
    original_name = original_name.orEmpty(),
    popularity = popularity ?: 0.0,
    profile_path = profile_path.orEmpty(),
)
