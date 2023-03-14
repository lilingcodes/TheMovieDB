package com.lilingxu.themoviedb.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    val PREFERENCE_FILE_KEY = "my prefs"

    val REQUEST_TOKEN= "request token"
    val SESSION_ID_KEY = "session id"

    val IS_LOGGED_KEY = "is logged"

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)


    fun getRequestToken():String = sharedPref.getString(REQUEST_TOKEN, "")!!
    fun setRequestToken(token: String) =
        sharedPref.edit().putString(REQUEST_TOKEN, token).apply()


    fun getSessionId(): String = sharedPref.getString(SESSION_ID_KEY, "")!!
    fun setSessionId(sessionId: String) =
        sharedPref.edit().putString(SESSION_ID_KEY, sessionId).apply()


    fun getIsLogged(): Boolean = sharedPref.getBoolean(IS_LOGGED_KEY, false)
    fun setIsLogged(isLogged: Boolean) =
        sharedPref.edit().putBoolean(IS_LOGGED_KEY, isLogged).apply()



    fun clear() {
        sharedPref.edit().clear().apply()
    }

}