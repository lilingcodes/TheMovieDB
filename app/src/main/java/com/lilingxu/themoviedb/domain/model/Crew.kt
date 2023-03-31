package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.details.CrewDto

class Crew(
    val adult: Boolean = false,
    val credit_id: String = "",
    val department: String = "",
    val gender: Int = 0,
    val id: Int = 0,
    val job: String = "",
    val known_for_department: String = "",
    val name: String = "",
    val original_name: String = "",
    val popularity: Double = 0.0,
    val profile_path: String = "",
)

fun CrewDto.toDomain(): Crew = Crew(
    adult = adult ?: false,
    credit_id = credit_id.orEmpty(),
    department = department.orEmpty(),
    gender = gender ?: 0,
    id = id ?: 0,
    job = job.orEmpty(),
    known_for_department = known_for_department.orEmpty(),
    name = name.orEmpty(),
    original_name = original_name.orEmpty(),
    popularity = popularity ?: 0.0,
    profile_path = profile_path.orEmpty(),
)