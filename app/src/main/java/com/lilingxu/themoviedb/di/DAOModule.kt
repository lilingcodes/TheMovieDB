package com.lilingxu.themoviedb.di

import com.google.firebase.firestore.CollectionReference
import com.lilingxu.themoviedb.data.network.firebase.AccountDAO
import com.lilingxu.themoviedb.data.network.firebase.AccountDAOImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DAOModule {

    @Singleton
    @Provides
    fun provideAccountDAO(accountsCollection: CollectionReference): AccountDAO =
        AccountDAOImpl(accountsCollection)

}