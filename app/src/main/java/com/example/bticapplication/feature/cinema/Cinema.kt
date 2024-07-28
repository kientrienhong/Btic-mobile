package com.example.bticapplication.feature.cinema

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.bticapplication.feature.cinemabrand.CinemaBrand
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "cinema",
    foreignKeys = [ForeignKey(
        entity = CinemaBrand::class,
        parentColumns = ["id"],
        childColumns = ["cinemaBrandId"]
    )]
)
data class Cinema(
    @PrimaryKey val id: Int,
    @SerialName("idCinemaBrand")
    val cinemaBrandId: Int,
    val name: String,
    val address: String,
)