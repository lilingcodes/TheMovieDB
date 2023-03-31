package com.lilingxu.themoviedb.data.repository

import com.lilingxu.themoviedb.data.network.services.DiscoverService
import com.lilingxu.themoviedb.data.network.services.GenresService
import com.lilingxu.themoviedb.data.network.services.MovieService
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Genre
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.domain.model.MovieDetails
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val discoverService: DiscoverService,
    private val genresService: GenresService,
) : MovieRepository {
    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails> {
        return movieService.getMovieDetails(movieId)
    }

    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return movieService.getPopular()
    }

    override suspend fun getNowPlayingMovies(): Resource<List<Movie>> {
        return movieService.getNowPlaying()
    }

    override suspend fun getUpcomingMovies(): Resource<List<Movie>> {
        return movieService.getUpcoming()
    }

    override suspend fun getTopRatedMovies(): Resource<List<Movie>> {
        return movieService.getTopRated()
    }


    override suspend fun getGenresTypeList(): Resource<List<Genre>> {
        return genresService.getMovieGenresList()
    }

    override suspend fun getMoviesByGenre(genreId: Int, page: Int): Resource<List<Movie>> {
        return discoverService.getMoviesByGenre(genreId, page)
    }


}