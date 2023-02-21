package com.lilingxu.themoviedb.data.network

import com.lilingxu.themoviedb.data.ResultAPI
import com.lilingxu.themoviedb.domain.model.Genre
import com.lilingxu.themoviedb.data.model.MovieResponseDto
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class TheMovieService @Inject constructor(
    private val api: TheMovieApi,
    @ApiToken private val token: String,
) {

    suspend fun getPopularMoviesService(): ResultAPI<List<Movie>> =
        getMoviesDomain(TheMovieApi::getPopularMoviesApi)

    suspend fun getNowPlayingMoviesService(): ResultAPI<List<Movie>> =
        getMoviesDomain(TheMovieApi::getNowPlayingMoviesApi)

    suspend fun getUpcomingMoviesService(): ResultAPI<List<Movie>> =
        getMoviesDomain(TheMovieApi::getUpcomingMoviesApi)

    suspend fun getTopRatedMoviesService(): ResultAPI<List<Movie>> =
        getMoviesDomain(TheMovieApi::getTopRatedMoviesApi)

    suspend fun getMoviesByGenre(genreId: Int, page: Int = 1): ResultAPI<List<Movie>>{
        return getMoviesDomain{
            this.getMoviesByGenre(it, genreId, page)
        }
    }

    private suspend fun getMoviesDomain(
        apiCall: suspend TheMovieApi.(String) -> Response<MovieResponseDto>,
    ): ResultAPI<List<Movie>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall(api, token)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        ResultAPI.Success(
                            body.results.map { it.toDomain() }
                        )
                    } else {
                        ResultAPI.Error("Empty response.")
                    }
                } else {
                    when (response.code()) {
                        401 -> ResultAPI.Error("Invalid API key: You must be granted a valid key.")
                        404 -> ResultAPI.Error("The resource you requested could not be found.")
                        else -> ResultAPI.Error("Unknown error.")
                    }
                }
            } catch (e: IOException) {
                ResultAPI.Error(message = "Couldn't reach server, check your internet connection")
            }
        }
    }

    suspend fun getGenresTypeService(): ResultAPI<List<Genre>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getGenresTypeApi(token)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        ResultAPI.Success(body.genres)
                    } else {
                        ResultAPI.Error("Empty response.")
                    }
                }else {
                    when (response.code()) {
                        401 -> ResultAPI.Error("Invalid API key: You must be granted a valid key.")
                        404 -> ResultAPI.Error("The resource you requested could not be found.")
                        else -> ResultAPI.Error("Unknown error.")
                    }
                }
            } catch (e: IOException) {
                ResultAPI.Error(message = "Couldn't reach server, check your internet connection")

            }
        }
    }


}