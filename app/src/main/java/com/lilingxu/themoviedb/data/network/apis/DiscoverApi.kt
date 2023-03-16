package com.lilingxu.themoviedb.data.network.apis

import com.lilingxu.themoviedb.data.model.movie.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverApi {

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") token: String,
        @Query("with_genres") genreId: Int,
        @Query(value = "page", encoded = true) page: Int? = null,
    ): Response<MovieResponseDto>

}