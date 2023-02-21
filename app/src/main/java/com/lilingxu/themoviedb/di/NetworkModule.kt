package com.lilingxu.themoviedb.di

import com.google.gson.GsonBuilder
import com.lilingxu.themoviedb.data.network.*
import com.lilingxu.themoviedb.data.repository.MovieRepositoryImpl
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import com.lilingxu.themoviedb.utils.AUTH_TOKEN
import com.lilingxu.themoviedb.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideTheMovieApi(): TheMovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(TheMovieApi::class.java)
    }



    @Singleton
    @Provides
    @ApiToken
    fun provideAuthToken(): String {
        return AUTH_TOKEN
    }

    @Singleton
    @Provides
    fun provideMovieRepository(api: TheMovieService): MovieRepository = MovieRepositoryImpl(api)

}