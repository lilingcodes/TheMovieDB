package com.lilingxu.themoviedb.data.model.movie.details

data class CreditsDto(
    val cast: List<CastDto>? = null,
    val crew: List<CrewDto>? = null,
)

data class CastDto(
    val adult: Boolean? = null,
    val cast_id: Int? = null,
    val character: String? = null,
    val credit_id: String? = null,
    val gender: Int? = null,
    val id: Int? = null,
    val known_for_department: String? = null,
    val name: String? = null,
    val order: Int? = null,
    val original_name: String? = null,
    val popularity: Double? = null,
    val profile_path: String? = null,
)

data class CrewDto(
    val adult: Boolean? = null,
    val credit_id: String? = null,
    val department: String? = null,
    val gender: Int? = null,
    val id: Int? = null,
    val job: String? = null,
    val known_for_department: String? = null,
    val name: String? = null,
    val original_name: String? = null,
    val popularity: Double? = null,
    val profile_path: String? = null,
)