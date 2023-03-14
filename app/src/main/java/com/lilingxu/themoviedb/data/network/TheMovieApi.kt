package com.lilingxu.themoviedb.data.network

import com.google.gson.JsonObject
import com.lilingxu.themoviedb.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TheMovieApi {
    //ACCOUNT
    @GET("account")
    suspend fun getAccountDetails(
        @Query("api_key") token: String,
        @Query("session_id") sessionId: String,
    ): Response<AccountDto>

    //AUTHENTICATION
    @GET("authentication/token/new")
    suspend fun createRequestToken(
        @Query("api_key") token: String,
    ): Response<TokenDto>

    @POST("authentication/session/new")
    suspend fun createSession(
        @Query("api_key") token: String,
        @Body request_token: JsonObject
    ): Response<SessionDto>

    @POST("authentication/token/validate_with_login")
    suspend fun createSessionWithLogin(
        @Query("api_key") token: String,
        @Body body: LoginModel
    ): Response<TokenDto>

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