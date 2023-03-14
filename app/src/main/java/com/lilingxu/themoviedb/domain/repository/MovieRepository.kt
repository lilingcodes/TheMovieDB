package com.lilingxu.themoviedb.domain.repository

import com.lilingxu.themoviedb.data.networkResult.ResultAPI
import com.lilingxu.themoviedb.domain.model.Genre
import com.lilingxu.themoviedb.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): ResultAPI<List<Movie>>
    suspend fun getNowPlayingMovies(): ResultAPI<List<Movie>>
    suspend fun getUpcomingMovies(): ResultAPI<List<Movie>>
    suspend fun getTopRatedMovies(): ResultAPI<List<Movie>>
    suspend fun getGenresTypeList(): ResultAPI<List<Genre>>
    suspend fun getMoviesByGenre(genreId: Int, page: Int = 1): ResultAPI<List<Movie>>

}