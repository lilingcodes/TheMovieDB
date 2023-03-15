package com.lilingxu.themoviedb.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Genre
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _genresList = MutableLiveData<List<Genre>>()
    val genresList: MutableLiveData<List<Genre>> get() = _genresList

    init {
        getGenres()
    }

    //TODO llamar una vez y guardarlo en local
    private fun getGenres(){
        viewModelScope.launch {
            when(val result = repository.getGenresTypeList()){
                is Resource.Success -> {
                    _genresList.value = result.data ?: emptyList()
                }
                is Resource.Error -> {
                    //TODO mostar un dialog de error
                }
                else -> {}
            }
        }
    }
}