package com.lilingxu.themoviedb.data.network.services

import com.lilingxu.themoviedb.data.network.ApiToken
import com.lilingxu.themoviedb.data.network.apis.MoviesApi
import com.lilingxu.themoviedb.data.network.getApiResource
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.domain.model.toDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieServiceImpl @Inject constructor(
    @ApiToken private val token: String,
    private val moviesApi: MoviesApi,
) : MovieService {

    override suspend fun getPopular(): Resource<List<Movie>> {
        return getApiResource(
            name = "getPopular",
            apiResponse = { moviesApi.getPopular(token) },
            onSuccess = { movieResponseDto ->
                movieResponseDto.results.map { it.toDomain() }
            }
        )
    }

    override suspend fun getNowPlaying(): Resource<List<Movie>> {
        return getApiResource(
            name = "getNowPlaying",
            apiResponse = { moviesApi.getNowPlaying(token) },
            onSuccess = { movieResponseDto ->
                movieResponseDto.results.map { it.toDomain() }
            }
        )
    }

    override suspend fun getUpcoming(): Resource<List<Movie>> {
        return getApiResource(
            name = "getUpcoming",
            apiResponse = { moviesApi.getUpcoming(token) },
            onSuccess = { movieResponseDto ->
                movieResponseDto.results.map { it.toDomain() }
            }
        )
    }

    override suspend fun getTopRated(): Resource<List<Movie>> {
        return getApiResource(
            name = "getTopRated",
            apiResponse = { moviesApi.getTopRated(token) },
            onSuccess = { movieResponseDto ->
                movieResponseDto.results.map { it.toDomain() }
            }
        )
    }

}