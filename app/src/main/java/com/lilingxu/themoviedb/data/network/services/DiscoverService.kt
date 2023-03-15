package com.lilingxu.themoviedb.data.network.services

import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Movie

interface DiscoverService {

    suspend fun getMoviesByGenre(genreId: Int, page: Int = 1) :Resource<List<Movie>>

}