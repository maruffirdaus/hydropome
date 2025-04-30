package com.motion.hydropome.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.motion.hydropome.data.repository.AuthRepository
import com.motion.hydropome.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): AuthRepository =
        AuthRepository(auth, firestore)

    @Provides
    @Singleton
    fun provideUserRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): UserRepository =
        UserRepository(auth, firestore)
}