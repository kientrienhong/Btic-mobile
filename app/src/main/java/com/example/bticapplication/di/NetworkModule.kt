package com.example.bticapplication.di

import com.example.bticapplication.feature.authen.AuthRemoteApi
import com.example.bticapplication.feature.cinema.CinemaBrandRemoteApi
import com.example.bticapplication.path.ServicePath
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
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
    fun provideCinemaBrandRemoteApi(retrofit: Retrofit): CinemaBrandRemoteApi =
        retrofit.create(CinemaBrandRemoteApi::class.java)

}