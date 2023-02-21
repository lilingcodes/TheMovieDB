package com.lilingxu.themoviedb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheMovieDBApplication: Application(){
    companion object{

    }
    override fun onCreate() {
        super.onCreate()

    }
}