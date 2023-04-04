package com.lilingxu.themoviedb.di

import android.content.Context
import com.lilingxu.themoviedb.data.network.firebase.AccountDAO
import com.lilingxu.themoviedb.data.network.services.*
import com.lilingxu.themoviedb.data.repository.AuthRepositoryImpl
import com.lilingxu.themoviedb.data.repository.MovieRepositoryImpl
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieService: MovieService,
        discoverService: DiscoverService,
        genresService: GenresService,
        @ApplicationContext context: Context
    ): MovieRepository = MovieRepositoryImpl(movieService, discoverService, genresService, context)

    @Singleton
    @Provides
    fun provideLoginRepository(
        accountDAO: AccountDAO,
        authService: AuthenticationService,
        accountService: AccountService,
    ): AuthRepository =
        AuthRepositoryImpl(accountDAO, authService, accountService)
}