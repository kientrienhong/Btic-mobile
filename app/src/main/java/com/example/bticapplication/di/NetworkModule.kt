package com.example.bticapplication.di

import android.content.Context
import com.example.bticapplication.feature.authen.AuthLocalApi
import com.example.bticapplication.feature.authen.AuthLocalApiImpl
import com.example.bticapplication.feature.authen.AuthRemoteApi
import com.example.bticapplication.feature.authen.AuthRepository
import com.example.bticapplication.feature.authen.AuthRepositoryImpl
import com.example.bticapplication.feature.splash.SplashScreenRepository
import com.example.bticapplication.path.ServicePath
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun bindAuthLocalApi(authLocalApiImpl: AuthLocalApiImpl): AuthLocalApi

    companion object {
        private val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }

        @Singleton
        @Provides
        fun providesOkHttpClient(): OkHttpClient =
            OkHttpClient
                .Builder()
                .build()

        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            val mediaType = "application/json".toMediaType()
            val jsonConverter = Json.asConverterFactory(mediaType)
            return Retrofit
                .Builder()
                .addConverterFactory(jsonConverter)
                .baseUrl(ServicePath.BASE_PATH)
                .client(okHttpClient)
                .build()
        }

        @Provides
        @Singleton
        fun provideAuthRemoteApi(retrofit: Retrofit): AuthRemoteApi =
            retrofit.create(AuthRemoteApi::class.java)

        @Provides
        @Singleton
        fun provideAuthRepository(
            context: Context,
            authLocalApi: AuthLocalApi,
            authRemoteApi: AuthRemoteApi
        ): AuthRepository = AuthRepositoryImpl(context, authRemoteApi, authLocalApi)

        @Provides
        fun provideApplicationContext(@ApplicationContext context: Context): Context = context

        @Provides
        @Singleton
        fun provideSplashScreenRepository(retrofit: Retrofit): SplashScreenRepository =
            retrofit.create(SplashScreenRepository::class.java)
    }
}