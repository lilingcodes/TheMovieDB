package com.lilingxu.themoviedb.data.network

import com.lilingxu.themoviedb.data.model.GenreResponseDto
import com.lilingxu.themoviedb.data.model.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieApi {

    //MOVIES
    @GET("movie/popular")
    suspend fun getPopularMoviesApi(
        @Query("api_key") token: String,
    ): Response<MovieResponseDto>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMoviesApi(
        @Query("api_key") token: String,
    ): Response<MovieResponseDto>

    @GET("movie/upcoming")
    suspend fun getUpcomingMoviesApi(
        @Query("api_key") token: String,
    ): Response<MovieResponseDto>

    @GET("movie/top_rated")
    suspend fun getTopRatedMoviesApi(
        @Query("api_key") token: String,
    ): Response<MovieResponseDto>

    //GENRES
    @GET("genre/movie/list")
    suspend fun getGenresTypeApi(
        @Query("api_key") token: String,
    ): Response<GenreResponseDto>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") token: String,
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int = 1,
    ): Response<MovieResponseDto>

}