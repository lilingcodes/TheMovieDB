package com.lilingxu.themoviedb.data.network.services

import com.lilingxu.themoviedb.data.network.ApiToken
import com.lilingxu.themoviedb.data.network.apis.GenresApi
import com.lilingxu.themoviedb.data.network.getApiResource
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Genre
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenresServiceImpl @Inject constructor(
    @ApiToken private val token: String,
    private val genresApi: GenresApi,
) : GenresService {

    override suspend fun getMovieGenresList(): Resource<List<Genre>> {
        return getApiResource(
            apiResponse = { genresApi.getMovieList(token) },
            onSuccess = { genreResponseDto ->
                genreResponseDto.genres
            }
        )
    }

}