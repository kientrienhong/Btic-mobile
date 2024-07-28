package com.example.bticapplication.feature.cinema

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CinemaRepositoryImpl(
    private val cinemaLocalApi: CinemaLocalApi,
    private val cinemaRemoteApi: CinemaRemoteApi
) : CinemaRepository {
    override suspend fun create(cinemaBrandId: Int, cinema: Cinema) {

    }

    override suspend fun getList(cinemaBrandId: Int): List<Cinema> = withContext(Dispatchers.IO) {
        val result = cinemaRemoteApi.getListCinema(cinemaBrandId)
        cinemaLocalApi.insertAll(result)
        result
    }

    override suspend fun delete(cinemaBrandId: Int, cinema: Cinema) {
        TODO("Not yet implemented")
    }
}