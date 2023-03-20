package com.lilingxu.themoviedb.data.network.services

import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.domain.model.MovieDetails

interface MovieService {

    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails>

    suspend fun getPopular(): Resource<List<Movie>>

    suspend fun getNowPlaying(): Resource<List<Movie>>

    suspend fun getUpcoming(): Resource<List<Movie>>

    suspend fun getTopRated(): Resource<List<Movie>>
}