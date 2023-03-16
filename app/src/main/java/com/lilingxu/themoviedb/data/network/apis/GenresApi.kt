package com.lilingxu.themoviedb.data.network.apis

import com.lilingxu.themoviedb.data.model.genre.GenreResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresApi {

    @GET("genre/movie/list")
    suspend fun getMovieList(
        @Query("api_key") token: String,
    ): Response<GenreResponseDto>

}