package com.example.bticapplication.di

import android.content.Context
import com.example.bticapplication.feature.authen.AuthLocalApi
import com.example.bticapplication.feature.authen.AuthRemoteApi
import com.example.bticapplication.feature.authen.AuthRepository
import com.example.bticapplication.feature.authen.AuthRepositoryImpl
import com.example.bticapplication.feature.cinema.CinemaBrandLocalApi
import com.example.bticapplication.feature.cinema.CinemaBrandRemoteApi
import com.example.bticapplication.feature.cinema.CinemaBrandRepository
import com.example.bticapplication.feature.cinema.CinemaBrandRepositoryImpl
import com.example.bticapplication.feature.splash.SplashScreenRepository
import com.example.bticapplication.feature.splash.SplashScreenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        context: Context,
        authLocalApi: AuthLocalApi,
        authRemoteApi: AuthRemoteApi
    ): AuthRepository = AuthRepositoryImpl(context, authRemoteApi, authLocalApi)

    @Provides
    @Singleton
    fun provideSplashScreenRepository(
        authRepository: AuthRepository,
        cinemaBrandRemoteApi: CinemaBrandRemoteApi,
        cinemaBrandLocalApi: CinemaBrandLocalApi
    ): SplashScreenRepository =
        SplashScreenRepositoryImpl(authRepository, cinemaBrandRemoteApi, cinemaBrandLocalApi)

    @Provides
    @Singleton
    fun provideCinemaBrandRepository(
        cinemaBrandRemoteApi: CinemaBrandRemoteApi,
        cinemaBrandLocalApi: CinemaBrandLocalApi
    ): CinemaBrandRepository = CinemaBrandRepositoryImpl(cinemaBrandLocalApi, cinemaBrandRemoteApi)

    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context = context
}