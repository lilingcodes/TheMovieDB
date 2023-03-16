package com.lilingxu.themoviedb.data.model.movie

import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
   @SerializedName("page") val page: Int,
   @SerializedName("results") val results: List<MovieDto>,
   @SerializedName("total_pages") val total_pages: Int,
   @SerializedName("total_results") val total_results: Int
)