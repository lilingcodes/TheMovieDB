package com.lilingxu.themoviedb.data.network.services

import com.lilingxu.themoviedb.data.network.ApiToken
import com.lilingxu.themoviedb.data.network.apis.DiscoverApi
import com.lilingxu.themoviedb.data.network.getApiResource
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.domain.model.toDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiscoverServiceImpl @Inject constructor(
    @ApiToken private val token: String,
    private val discoverApi: DiscoverApi,
) : DiscoverService {

    override suspend fun getMoviesByGenre(genreId: Int, page: Int): Resource<List<Movie>> {
        return getApiResource(
            name = "getMoviesByGenre",
            apiResponse = { discoverApi.getMoviesByGenre(token, genreId, page) },
            onSuccess = { movieResponseDto ->
                movieResponseDto.results.map { it.toDomain() }
            }
        )
    }

}