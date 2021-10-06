package com.sudo.data.di

import android.content.Context
import androidx.room.Room
import com.sudo.data.local.database.MedamiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MedamiDatabase::class.java,
        "MedamiDatabase"
    ).build()

    @Provides
    fun provideMedamiDao(
        database: MedamiDatabase
    ) = database.medamiDao

}