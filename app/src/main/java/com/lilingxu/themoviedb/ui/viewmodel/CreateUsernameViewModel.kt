package com.lilingxu.themoviedb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateUsernameViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val firebaseDB: FirebaseFirestore
) : ViewModel() {

    private val _username = MutableLiveData<String>("")
    val username: LiveData<String> get() = _username

    private val _isEnable = MutableLiveData<Boolean>(false)
    val isEnable: LiveData<Boolean> get() = _isEnable

    fun onUsernameChange(username: String) {
        _username.value = username
    }
}
