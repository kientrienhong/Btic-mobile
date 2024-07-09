package com.example.bticapplication.feature.cinema

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CinemaBrandRepositoryImpl @Inject constructor(
    private val cinemaBrandLocalApi: CinemaBrandLocalApi,
    private val cinemaBrandRemoteApi: CinemaBrandRemoteApi
) : CinemaBrandRepository {
    override fun getList(): LiveData<List<CinemaBrand>> = liveData {
        withContext(Dispatchers.IO) {
            emitSource(cinemaBrandLocalApi.getList())
        }
    }

    override suspend fun fetchList(): Unit = withContext(Dispatchers.IO) {
        val list = cinemaBrandRemoteApi.getList()
        cinemaBrandLocalApi.saveListCinema(list)
    }

    override suspend fun create(cinemaBrand: CinemaBrand) {
        val newCinema = cinemaBrandRemoteApi.create(cinemaBrand)
        cinemaBrandLocalApi.saveCinema(newCinema)
    }

    override suspend fun delete(cinemaBrand: CinemaBrand) {
        cinemaBrandRemoteApi.delete(cinemaBrand.id)
        cinemaBrandLocalApi.deleteCinema(cinemaBrand)
    }

    override suspend fun update(cinemaBrand: CinemaBrand) {
        TODO("Not yet implemented")
    }
}