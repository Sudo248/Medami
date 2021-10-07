package com.sudo.data.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sudo.data.local.database.MedamiDatabase
import com.sudo.data.remote.firebase.service.FirebaseService
import com.sudo.data.remote.firebase.service.FirebaseServiceImpl
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

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFireStore() = FirebaseFirestore.getInstance()

//    @Provides
//    @Singleton
//    fun provideFirebaseService(
//        firebaseAuth: FirebaseAuth,
//        fireStore: FirebaseFirestore
//    ) = FirebaseServiceImpl(firebaseAuth, fireStore) as FirebaseService

}