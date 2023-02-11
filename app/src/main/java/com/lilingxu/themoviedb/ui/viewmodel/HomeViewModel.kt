package com.lilingxu.themoviedb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lilingxu.themoviedb.data.ResultAPI
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
        getPopularMovies()
        getNowPlayingMovies()
        getUpcomingMovies()
        getTopRatedMovies()
    }


    private fun getPopularMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = repository.getPopularMovies()) {
                is ResultAPI.Success -> {
                    _isLoading.value = false
                    _popularMovies.value = result.data ?: emptyList()
                }
                is ResultAPI.Error -> {
                    _isLoading.value = false
                }
            }
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            when (val result = repository.getNowPlayingMovies()) {
                is ResultAPI.Success -> {
                    _isLoading.value = false
                    _nowPlayingMovies.value = result.data ?: emptyList()
                }
                is ResultAPI.Error -> {
                    _isLoading.value = false
                }
            }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            when (val result = repository.getUpcomingMovies()) {
                is ResultAPI.Success -> {
                    _isLoading.value = false
                    _upcomingMovies.value = result.data ?: emptyList()
                }
                is ResultAPI.Error -> {
                    _isLoading.value = false
                }
            }
        }
    }


    private fun getTopRatedMovies() {
        viewModelScope.launch {
            when (val result = repository.getTopRatedMovie()) {
                is ResultAPI.Success -> {
                    _isLoading.value = false
                    _topRatedMovies.value = result.data ?: emptyList()
                }
                is ResultAPI.Error -> {
                    _isLoading.value = false
                }
            }
        }
    }

}

