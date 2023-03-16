package com.lilingxu.themoviedb.di

import com.lilingxu.themoviedb.data.network.firebase.AccountDAO
import com.lilingxu.themoviedb.data.network.services.*
import com.lilingxu.themoviedb.data.repository.AuthRepositoryImpl
import com.lilingxu.themoviedb.data.repository.MovieRepositoryImpl
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    ): MovieRepository = MovieRepositoryImpl(movieService, discoverService, genresService)

    @Singleton
    @Provides
    fun provideLoginRepository(
        accountDAO: AccountDAO,
        authService: AuthenticationService,
        accountService: AccountService,
    ): AuthRepository =
        AuthRepositoryImpl(accountDAO, authService, accountService)
}