package com.sudo.medami.di

import com.sudo.domain.repositories.user.UserRepository
import com.sudo.domain.use_cases.user.LogInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {


    @Provides
    @Singleton
    fun provideLogInUseCase(
        repository: UserRepository
    ) = LogInUseCase(repository)


}