package com.lilingxu.themoviedb.ui.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lilingxu.themoviedb.TheMovieDBApplication
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthRequestScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    val EXPIRED_TIME = 60000L
    private var countDownTimer: CountDownTimer? = null

    private val _secondsLeft = MutableLiveData(0)
    val secondsLeft: MutableLiveData<Int> get() = _secondsLeft

    private val _requestToken = MutableLiveData<String>("")
    val requestToken: MutableLiveData<String> get() = _requestToken

    private val _sessionId = MutableLiveData<String>("")
    val sessionId: MutableLiveData<String> get() = _sessionId


    init {
        startTimer()
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(EXPIRED_TIME, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Se llama cada segundo durante los 30 segundos
                val secondsLeft = millisUntilFinished / 1000
                // Aquí puedes actualizar una UI o hacer algo con el tiempo restante
                _secondsLeft.value = secondsLeft.toInt()
            }

            override fun onFinish() {
                // Se llama cuando el recuento regresivo termina (después de 30 segundos)
            }
        }

        // Iniciar el contador
        countDownTimer?.start()
    }

    override fun onCleared() {
        super.onCleared()
        // Detener el temporizador cuando se limpie el ViewModel
        countDownTimer?.cancel()
    }


    fun createSessionIdWithToken(requestToken: String) {
        viewModelScope.launch {
            when (val it = authRepository.createSession(requestToken)) {
                is Resource.Success -> {
                    val sessionId = it.data ?: ""
                    TheMovieDBApplication.sharedPref.setSessionId(sessionId)
                    _sessionId.value = sessionId
                    Log.e(
                        "PRUEBAS",
                        "nuevo usuario creado: ${sessionId}  procedemos a guardarlo en DB"
                    )
                    saveNewUser(sessionId)
                }
                else -> {

                }
            }
        }
    }


    private fun saveNewUser(sessionId: String) {
        viewModelScope.launch {
            if (authRepository.getAccountDetails(sessionId) is Resource.Success) {
                val account = authRepository.getAccountDetails(sessionId).data
                if (account != null) {
                    account.sessionId = sessionId
                    if (authRepository.saveNewAccount(account) is Resource.Success) {
                        Log.e("PRUEBAS", "nuevo usuario SUCCES: ${sessionId}  ")
                    }
                }
            }
        }
    }


}