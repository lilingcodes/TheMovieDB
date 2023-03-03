package com.lilingxu.themoviedb.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.lilingxu.themoviedb.R
import com.lilingxu.themoviedb.data.network.AuthService
import com.lilingxu.themoviedb.data.network.GoogleToken
import com.lilingxu.themoviedb.data.repository.AuthRepositoryImpl
import com.lilingxu.themoviedb.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
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

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage(): StorageReference =
        FirebaseStorage.getInstance().reference.child("profilePics")


    @Provides
    @Singleton
    fun provideUserCollection(db: FirebaseFirestore): CollectionReference = db.collection("users")

    @Singleton
    @Provides
    fun provideLoginRepository(authService: AuthService): AuthRepository =
        AuthRepositoryImpl(authService)

}