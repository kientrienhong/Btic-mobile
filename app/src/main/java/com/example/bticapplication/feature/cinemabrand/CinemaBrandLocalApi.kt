package com.example.bticapplication.feature.cinemabrand

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CinemaBrandLocalApi {
    @Query("SELECT * FROM cinema_brand ORDER BY id DESC")
    fun getList(): LiveData<List<CinemaBrand>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCinema(cinemaBrand: CinemaBrand)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(listCinema: List<CinemaBrand>)

    @Query("DELETE FROM cinema_brand")
    suspend fun clear()

    @Transaction
    suspend fun saveListCinema(listCinema: List<CinemaBrand>) {
        clear()
        insertList(listCinema)
    }

    @Delete
    suspend fun deleteCinema(cinemaBrand: CinemaBrand)
}