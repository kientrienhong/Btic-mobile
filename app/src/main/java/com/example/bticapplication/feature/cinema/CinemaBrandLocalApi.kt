package com.example.bticapplication.feature.cinema

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CinemaBrandLocalApi {
    @Query("SELECT * FROM cinema_brand ORDER BY id DESC")
    fun getList(): LiveData<List<CinemaBrand>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCinema(cinemaBrand: CinemaBrand)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveListCinema(listCinema: List<CinemaBrand>)
}