package com.lilingxu.themoviedb.data.model.movie

import com.lilingxu.themoviedb.data.model.BelongsToCollection
import com.lilingxu.themoviedb.data.model.ProductionCompany
import com.lilingxu.themoviedb.data.model.ProductionCountry
import com.lilingxu.themoviedb.data.model.SpokenLanguage
import com.lilingxu.themoviedb.domain.model.Genre

data class MovieDetailsResponseDto(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val belongs_to_collection: BelongsToCollection? = null,
    val budget: Int? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<ProductionCompany>? = null,
    val production_countries: List<ProductionCountry>? = null,
    val release_date: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val spoken_languages: List<SpokenLanguage>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
)