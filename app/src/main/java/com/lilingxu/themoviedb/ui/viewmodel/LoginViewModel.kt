package com.lilingxu.themoviedb.ui.viewmodel

import android.content.Intent
import android.util.Log
import android.util.Patterns
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.lilingxu.themoviedb.data.ResultAPI
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val googleSignInClient: GoogleSignInClient,
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {

    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _isLoginEnable = MutableLiveData(false)
    val isLoginEnable: LiveData<Boolean> get() = _isLoginEnable

    private val _isLogged = MutableLiveData(false)
    val isLogged: LiveData<Boolean> get() = _isLogged

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean> get() = _error


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password

        _isLoginEnable.value = isValidEmail(email) && _password.value != ""
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()


    fun login(loginOnClick: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            //TODO autenticacion api service y google
            delay(2000)
            _isLoading.value = false
            loginOnClick()
        }
    }

    fun loginWithCredential(activityResult: ActivityResult, onLoginSuccess: ()->Unit, onCreateNewUser: () -> Unit) {
        viewModelScope.launch {
            val task = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                repository.loginWithCredential(credential).collect {
                    when (it) {
                        is ResultAPI.Success<*> -> {
                            if (it.data?.additionalUserInfo?.isNewUser == true){
                                onCreateNewUser()
                            }else{
                                onLoginSuccess()
                            }
                            _isLoading.value = false

                        }

                        is ResultAPI.Loading<*> -> {
                            _isLoading.value = true
                        }

                        is ResultAPI.Error<*> -> {
                            //TODO mostar un dialog de error
                            _isLoading.value = false
                        }
                    }

                }
            } catch (e: ApiException) {
                Log.e("TAG", "Google sign in failed", e)
            }
        }

    }

    fun loginWithGoogle(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        googleSignInClient.signOut()
        launcher.launch(googleSignInClient.signInIntent)
    }


}

