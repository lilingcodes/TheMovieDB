package com.lilingxu.themoviedb.di

import com.lilingxu.themoviedb.data.network.ApiToken
import com.lilingxu.themoviedb.data.network.apis.*
import com.lilingxu.themoviedb.data.network.services.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideAccountService(
        @ApiToken token: String,
        accountApi: AccountApi,
    ): AccountService = AccountServiceImpl(token, accountApi)

    @Singleton
    @Provides
    fun provideAuthenticationService(
        @ApiToken token: String,
        authenticationApi: AuthenticationApi,
    ): AuthenticationService = AuthenticationServiceImpl(token, authenticationApi)

    @Singleton
    @Provides
    fun provideDiscoverService(
        @ApiToken token: String,
        discoverApi: DiscoverApi,
    ): DiscoverService = DiscoverServiceImpl(token, discoverApi)

    @Singleton
    @Provides
    fun provideGenresService(
        @ApiToken token: String,
        genresApi: GenresApi,
    ): GenresService = GenresServiceImpl(token, genresApi)

    @Singleton
    @Provides
    fun provideMoviesService(
        @ApiToken token: String,
        moviesApi: MoviesApi,
    ): MovieService = MovieServiceImpl(token, moviesApi)

}