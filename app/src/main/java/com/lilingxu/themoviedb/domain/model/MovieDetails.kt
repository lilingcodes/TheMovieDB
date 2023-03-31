package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.MovieDetailsResponseDto
import java.util.*

data class MovieDetails(
    val movie: Movie = Movie(),

    val status: String = "",
    val runtime: Int = 0,
    val genres: List<Genre> = emptyList(),
    val tagline: String = "",
    val budget: Int = 0,
    val revenue: Int = 0,
    val homepage: String = "",

    val production_companies: List<ProductionCompany> = emptyList(),
    val production_countries: List<ProductionCountry> = emptyList(),
    val spoken_languages: List<SpokenLanguage> = emptyList(),
    val belongs_to_collection: BelongsToCollection = BelongsToCollection(),

    val keywords: List<Keyword> = emptyList(),
    val externalIds: ExternalIds = ExternalIds(),
    val casts: List<Cast> = emptyList(),
    val crews: List<Crew> = emptyList(),
    val recommendations: List<Movie> = emptyList(),
    val similar: List<Movie> = emptyList(),
    val videos: List<Video> = emptyList(),

    )

fun MovieDetailsResponseDto.toDomain() = MovieDetails(
    movie = Movie(
        id = id ?: 0,
        adult = adult ?: false,
        title = title.orEmpty(),
        poster_path = poster_path.orEmpty(),
        backdrop_path = backdrop_path.orEmpty(),
        overview = overview.orEmpty(),
        release_date = dateFormatter.parseOrNull(release_date) ?: Date(),
        popularity = popularity ?: 0.0,
        vote_count = vote_count ?: 0,
        vote_average = vote_average ?: 0.0,
    ),
    status = status.orEmpty(),
    runtime = runtime ?: 0,
    genres = genres.orEmpty(),
    tagline = tagline.orEmpty(),
    budget = budget ?: 0,
    revenue = revenue ?: 0,
    homepage = homepage.orEmpty(),

    production_companies = production_companies?.map { it.toDomain() }.orEmpty(),
    production_countries = production_countries?.map { it.toDomain() }.orEmpty(),
    spoken_languages = spoken_languages?.map { it.toDomain() }.orEmpty(),
    belongs_to_collection = belongs_to_collection?.toDomain() ?: BelongsToCollection(),

    keywords = keywords?.keyword?.map { it.toDomain() }.orEmpty(),
    externalIds = external_ids?.toDomain() ?: ExternalIds(),
    casts = credits?.cast?.map { it.toDomain() }.orEmpty(),
    crews = credits?.crew?.map { it.toDomain() }.orEmpty(),
    recommendations = recommendations?.results?.map { it.toDomain() }.orEmpty(),
    similar = similar?.results?.map { it.toDomain() }.orEmpty(),
    videos = videos?.results?.map { it.toDomain() }.orEmpty(),
)