package com.example.bticapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bticapplication.feature.cinema.Cinema
import com.example.bticapplication.feature.cinema.CinemaLocalApi
import com.example.bticapplication.feature.cinemabrand.CinemaBrand
import com.example.bticapplication.feature.cinemabrand.CinemaBrandLocalApi

@Database(entities = [CinemaBrand::class, Cinema::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cinemaBrandLocalApi(): CinemaBrandLocalApi
    abstract fun cinemaLocalApi(): CinemaLocalApi

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}