package com.lilingxu.themoviedb.domain.model

import com.lilingxu.themoviedb.data.model.movie.details.ExternalIdsDto

data class ExternalIds(
    val facebook_id: String = "",
    val imdb_id: String = "",
    val instagram_id: String = "",
    val twitter_id: String = "",
    val wikidata_id: String = "",
)

fun ExternalIdsDto.toDomain(): ExternalIds = ExternalIds(
    facebook_id = facebook_id.orEmpty(),
    imdb_id = imdb_id.orEmpty(),
    instagram_id = instagram_id.orEmpty(),
    twitter_id = twitter_id.orEmpty(),
    wikidata_id = wikidata_id.orEmpty(),
)