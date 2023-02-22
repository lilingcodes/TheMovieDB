package com.lilingxu.themoviedb.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    val PREFERENCE_FILE_KEY = "my prefs"
    val USER_ACCOUNT_KEY = "user account"
    val IS_LOGGED_KEY = "is logged"

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)


    fun setUserAccount(email: String) =
        sharedPref.edit().putString(USER_ACCOUNT_KEY, email).apply()

    fun setIsLogged(isLogged: Boolean) =
        sharedPref.edit().putBoolean(IS_LOGGED_KEY, isLogged).apply()


    fun getUserAccount(): String = sharedPref.getString(USER_ACCOUNT_KEY, "")!!

    fun getIsLogged(): Boolean = sharedPref.getBoolean(IS_LOGGED_KEY, false)

    fun clear() {
        sharedPref.edit().clear().apply()
    }

}