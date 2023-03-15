package com.lilingxu.themoviedb.data.network.services

import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Genre

interface GenresService {

    suspend fun getMovieGenresList() : Resource<List<Genre>>

}