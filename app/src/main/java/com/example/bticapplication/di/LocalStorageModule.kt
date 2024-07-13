package com.example.bticapplication.di

import android.content.Context
import androidx.room.Room
import com.example.bticapplication.db.AppDatabase
import com.example.bticapplication.feature.authen.AuthLocalApi
import com.example.bticapplication.feature.authen.AuthLocalApiImpl
import com.example.bticapplication.feature.cinema.CinemaLocalApi
import com.example.bticapplication.feature.cinemabrand.CinemaBrandLocalApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalStorageModule {
    @Binds
    @Singleton
    abstract fun bindAuthLocalApi(authLocalApiImpl: AuthLocalApiImpl): AuthLocalApi

    companion object {
        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
            return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "room_database"
            ).build()
        }

        @Provides
        @Singleton
        fun provideCinemaBrandLocalApi(appDatabase: AppDatabase): CinemaBrandLocalApi =
            appDatabase.cinemaBrandLocalApi()

        @Provides
        @Singleton
        fun provideCinemaLocalApi(appDatabase: AppDatabase): CinemaLocalApi =
            appDatabase.cinemaLocalApi()
    }
}