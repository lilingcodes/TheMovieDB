package com.lilingxu.themoviedb.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthRequestScreenViewModel @Inject constructor(
    private val repository: MovieRepository,
):ViewModel() {


}