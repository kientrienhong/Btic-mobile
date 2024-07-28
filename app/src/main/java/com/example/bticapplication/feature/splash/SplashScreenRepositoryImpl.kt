package com.example.bticapplication.feature.splash

import com.example.bticapplication.feature.authen.AuthRepository
import com.example.bticapplication.feature.authen.model.RefreshAccessRequest
import com.example.bticapplication.feature.authen.model.User
import com.example.bticapplication.feature.cinema.CinemaLocalApi
import com.example.bticapplication.feature.cinemabrand.CinemaBrand
import com.example.bticapplication.feature.cinemabrand.CinemaBrandLocalApi
import com.example.bticapplication.feature.cinemabrand.CinemaBrandRemoteApi
import javax.inject.Inject

class SplashScreenRepositoryImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val cinemaBrandRemoteApi: CinemaBrandRemoteApi,
    private val cinemaBrandLocalApi: CinemaBrandLocalApi,
    private val cinemaLocalApi: CinemaLocalApi
) : SplashScreenRepository {
    override suspend fun refreshAccessToken(email: String, refreshToken: String): User {
        val response = authRepository.refreshAccessToken(RefreshAccessRequest(email, refreshToken))
        return response.user
    }

    override suspend fun getBrandCinemaList(): List<CinemaBrand> = cinemaBrandRemoteApi.getList()

    override suspend fun getMovieList() {
        TODO("Not yet implemented")
    }

    override suspend fun saveCinemaBrandList(cinemaBrandList: List<CinemaBrand>) {
        cinemaLocalApi.clear()
        cinemaBrandLocalApi.saveListCinema(cinemaBrandList)
    }
}