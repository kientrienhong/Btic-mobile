package com.example.bticapplication.feature.cinema

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CinemaBrandLocalApi {
    @Query("SELECT * FROM cinema_brand")
    suspend fun getList(): List<CinemaBrand>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveListCinema(listCinema: List<CinemaBrand>)
}