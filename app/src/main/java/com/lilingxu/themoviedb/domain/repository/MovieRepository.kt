package com.lilingxu.themoviedb.domain.repository

import com.lilingxu.themoviedb.data.ResultAPI
import com.lilingxu.themoviedb.domain.model.Movie

interface MovieRepository {

    suspend fun getPopularMovies(): ResultAPI<List<Movie>>
    suspend fun getNowPlayingMovies(): ResultAPI<List<Movie>>
    suspend fun getUpcomingMovies(): ResultAPI<List<Movie>>
    suspend fun getTopRatedMovie(): ResultAPI<List<Movie>>

}