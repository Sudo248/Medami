package com.sudo.medami.di

import com.sudo.data.remote.firebase.service.FirebaseService
import com.sudo.data.repositories.user.UserRepositoryImplFB
import com.sudo.domain.repositories.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        firebaseService: FirebaseService
    ) = UserRepositoryImplFB(firebaseService) as UserRepository

}