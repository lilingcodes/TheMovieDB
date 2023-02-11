package com.lilingxu.themoviedb.data.repository

import com.lilingxu.themoviedb.data.ResultAPI
import com.lilingxu.themoviedb.data.network.TheMovieService
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: TheMovieService,
) : MovieRepository {

    override suspend fun getPopularMovies(): ResultAPI<List<Movie>> {
        return api.getPopularMoviesService()
    }

    override suspend fun getNowPlayingMovies(): ResultAPI<List<Movie>> {
        return api.getNowPlayingMoviesService()
    }

    override suspend fun getUpcomingMovies(): ResultAPI<List<Movie>> {
       return api.getUpcomingMoviesService()
    }

    override suspend fun getTopRatedMovie(): ResultAPI<List<Movie>> {
        return api.getPopularMoviesService()
    }


}