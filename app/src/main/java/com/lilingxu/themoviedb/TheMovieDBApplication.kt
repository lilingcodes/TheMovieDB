package com.lilingxu.themoviedb

import android.app.Application
import com.lilingxu.themoviedb.data.local.SharedPref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheMovieDBApplication : Application() {
    companion object {
        lateinit var sharedPref: SharedPref
    }

    override fun onCreate() {
        super.onCreate()
        sharedPref = SharedPref(applicationContext)
    }
}