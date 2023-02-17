package com.lilingxu.themoviedb.domain.useCase

import com.lilingxu.themoviedb.data.ResultAPI
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import javax.inject.Inject


class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): ResultAPI<List<Movie>> {
        return repository.getPopularMovies()
    }

}