package com.lilingxu.themoviedb.data.network.apis

import com.lilingxu.themoviedb.data.model.movie.MovieDetailsResponseDto
import com.lilingxu.themoviedb.data.model.movie.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query(value = "append_to_response", encoded = true) append_to_response: String? = null,
    ): Response<MovieDetailsResponseDto>


    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") token: String,
    ): Response<MovieResponseDto>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") token: String,
    ): Response<MovieResponseDto>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") token: String,
    ): Response<MovieResponseDto>

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") token: String,
    ): Response<MovieResponseDto>
}