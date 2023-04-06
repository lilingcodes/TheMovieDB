package com.lilingxu.themoviedb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.HomeData
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _homeDataList = MutableLiveData<List<HomeData>>(emptyList())
    val homeDataList: MutableLiveData<List<HomeData>> get() = _homeDataList


    init {
        getAllMovies()

    }

    private fun getAllMovies() {
        viewModelScope.launch {

            repository.getHomeData().collect {
                when (it) {
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Success -> {
                        _homeDataList.value = it.data ?: emptyList()
                        _isLoading.value = false

                    }

                    is Resource.Error -> {
                        _isLoading.value = false
                    }
                }
            }
        }
    }
}

