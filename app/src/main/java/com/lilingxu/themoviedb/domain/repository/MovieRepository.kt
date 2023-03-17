package com.lilingxu.themoviedb.domain.repository

import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Genre
import com.lilingxu.themoviedb.domain.model.Movie

interface MovieRepository {

    suspend fun getPopularMovies(): Resource<List<Movie>>

    suspend fun getNowPlayingMovies(): Resource<List<Movie>>

    suspend fun getUpcomingMovies(): Resource<List<Movie>>

    suspend fun getTopRatedMovies(): Resource<List<Movie>>

    suspend fun getGenresTypeList(): Resource<List<Genre>>

    suspend fun getMoviesByGenre(genreId: Int, page: Int = 1): Resource<List<Movie>>

}