package com.example.bticapplication.feature.cinema

import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.bticapplication.feature.cinemabrand.CinemaBrand
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "cinema")
data class Cinema(
    @PrimaryKey val id: Int,
    @Relation(parentColumn = "id", entityColumn = "idCinemaBrand", associateBy = Junction(
        CinemaBrand::class
    ))
    val idCinemaBrand: Int,
    val name: String,
    val address: String,
)