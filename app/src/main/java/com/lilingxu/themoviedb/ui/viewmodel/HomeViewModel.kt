package com.lilingxu.themoviedb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _popularMovies = MutableLiveData<List<Movie>>(emptyList())
    val popularMovies: MutableLiveData<List<Movie>> get() = _popularMovies

    private val _nowPlayingMovies = MutableLiveData<List<Movie>>(emptyList())
    val nowPlayingMovies: MutableLiveData<List<Movie>> get() = _nowPlayingMovies

    private val _upcomingMovies = MutableLiveData<List<Movie>>(emptyList())
    val upcomingMovies: MutableLiveData<List<Movie>> get() = _upcomingMovies

    private val _topRatedMovies = MutableLiveData<List<Movie>>(emptyList())
    val topRatedMovies: MutableLiveData<List<Movie>> get() = _topRatedMovies


    init {
        _isLoading.value = true
        getPopularMovies()
        getNowPlayingMovies()
        getUpcomingMovies()
        getTopRatedMovies()
        _isLoading.value = false
        //TODO SHIMMER
    }

    private fun getPopularMovies() {
        getMovies(MovieRepository::getPopularMovies, _popularMovies)
    }

    private fun getNowPlayingMovies() {
        getMovies(MovieRepository::getNowPlayingMovies, _nowPlayingMovies)
    }

    private fun getUpcomingMovies() {
        getMovies(MovieRepository::getUpcomingMovies, _upcomingMovies)
    }

    private fun getTopRatedMovies() {
        getMovies(MovieRepository::getTopRatedMovies, _topRatedMovies)
    }

    private fun getMovies(
        apiCall: suspend MovieRepository.() -> Resource<List<Movie>>,
        movieList: MutableLiveData<List<Movie>>,
    ) {
        viewModelScope.launch {
            when (val result = apiCall(repository)) {
                is Resource.Success -> {
                    movieList.value = result.data ?: emptyList()
                }
                is Resource.Error -> {
                    //TODO mostar un dialog de error
                }
                else -> {}
            }
        }
    }
}

