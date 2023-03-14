package com.lilingxu.themoviedb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lilingxu.themoviedb.data.networkResult.ResultAPI
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GenreViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _movieList = MutableLiveData<List<Movie>>(mutableListOf())
    val movieList: MutableLiveData<List<Movie>> get() = _movieList

    private val _currentPage = MutableLiveData<Int>(1)

    private val _genreId = MutableLiveData<Int>(0)

    fun loadNextPage(genreId: Int) {
        _isLoading.value = true

        val current = _currentPage.value ?: 1

        if (genreId != _genreId.value) {
            _movieList.value = emptyList()
            _genreId.value = genreId
            _currentPage.value = 1
        }else {
            _currentPage.value = current + 1
        }

        _currentPage.value?.let {
            getMovies(_movieList) {
                repository.getMoviesByGenre(genreId, it)
            }
        }

        _isLoading.value = false
    }

    private fun getMovies(
        movieList: MutableLiveData<List<Movie>>,
        apiCall: suspend MovieRepository.() -> ResultAPI<List<Movie>>,
    ) {
        viewModelScope.launch {
            when (val result = apiCall(repository)) {
                is ResultAPI.Success -> {
                    result.data?.let { newMovies ->
                        val currentList = movieList.value
                        currentList?.let { list ->
                            movieList.value = (list + newMovies)
                        }
                    }
                }
                is ResultAPI.Error -> {
                    //TODO mostar un dialog de error
                }
                else -> {}
            }
        }
    }
}