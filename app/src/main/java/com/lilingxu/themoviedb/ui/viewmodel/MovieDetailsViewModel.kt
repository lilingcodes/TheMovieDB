package com.lilingxu.themoviedb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.MovieDetails
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _movieDetails = MutableStateFlow(MovieDetails())
    val movieDetails: StateFlow<MovieDetails> get() = _movieDetails


    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            if (movieRepository.getMovieDetails(id) is Resource.Success){
                val result = movieRepository.getMovieDetails(id)
                _movieDetails.value = result.data!!
            }else{

            }
        }
    }
}