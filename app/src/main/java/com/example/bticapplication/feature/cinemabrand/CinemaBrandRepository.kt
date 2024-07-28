package com.example.bticapplication.feature.cinemabrand

import androidx.lifecycle.LiveData

interface CinemaBrandRepository {
    fun getList(): LiveData<List<CinemaBrand>>

    /**
     * Fetching step:
     * 1. Get list from remote
     * 2. Save data to local
     */
    suspend fun fetchList()

    suspend fun create(cinemaBrand: CinemaBrand)

    suspend fun delete(cinemaBrand: CinemaBrand)
}