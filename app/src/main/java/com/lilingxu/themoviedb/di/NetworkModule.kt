package com.lilingxu.themoviedb.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.GsonBuilder
import com.lilingxu.themoviedb.R
import com.lilingxu.themoviedb.data.network.*
import com.lilingxu.themoviedb.data.repository.MovieRepositoryImpl
import com.lilingxu.themoviedb.data.repository.AuthRepositoryImpl
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import com.lilingxu.themoviedb.utils.AUTH_TOKEN
import com.lilingxu.themoviedb.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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



    @Singleton
    @Provides
    fun provideGoogleSignInClient(
        @GoogleToken token: String,
        @ApplicationContext context: Context,
    ): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }

    @Singleton
    @Provides
    @GoogleToken
    fun provideGoogleToken(@ApplicationContext context: Context): String {
        return context.getString(R.string.default_web_client_id)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideLoginRepository(firebaseAuth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(firebaseAuth)
}