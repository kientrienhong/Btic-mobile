package com.example.bticapplication.feature.cinema

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CinemaLocalApi {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(cinema: Cinema)

    @Query("DELETE FROM cinema")
    suspend fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cinemas: List<Cinema>)

    @Delete
    suspend fun delete(cinema: Cinema)
}