package com.lilingxu.themoviedb.data.model.movie

import com.lilingxu.themoviedb.data.model.movie.details.*
import com.lilingxu.themoviedb.domain.model.Genre

data class MovieDetailsResponseDto(
    val id: Int? = null,
    val adult: Boolean? = null,
    val title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val backdrop_path: String? = null,
    val release_date: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,

    val status: String? = null,
    val runtime: Int? = null,
    val genres: List<Genre>? = null,
    val tagline: String? = null,
    val budget: Int? = null,
    val revenue: Int? = null,
    val homepage: String? = null,

    val production_companies: List<ProductionCompanyDto>? = null,
    val production_countries: List<ProductionCountryDto>? = null,
    val spoken_languages: List<SpokenLanguageDto>? = null,
    val belongs_to_collection: BelongsToCollectionDto? = null,
    //val video: Boolean? = null,
    //val imdb_id: String? = null,
    //val original_language: String? = null,
    //val original_title: String? = null,

    val keywords: KeywordsDto? = null,
    val external_ids: ExternalIdsDto? = null,
    val credits: CreditsDto? = null,
    val recommendations: RecommendationsDto? = null,
    val similar: SimilarDto? = null,
    val videos: VideosDto? = null,
)