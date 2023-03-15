package com.lilingxu.themoviedb.di

import com.google.gson.GsonBuilder
import com.lilingxu.themoviedb.data.network.*
import com.lilingxu.themoviedb.data.network.apis.*
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
    @ApiToken
    fun provideAuthToken(): String {
        return AUTH_TOKEN
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    }

    @Singleton
    @Provides
    fun provideAccountApi(retrofit: Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDiscoverApi(retrofit: Retrofit): DiscoverApi {
        return retrofit.create(DiscoverApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGenresApi(retrofit: Retrofit): GenresApi {
        return retrofit.create(GenresApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

}