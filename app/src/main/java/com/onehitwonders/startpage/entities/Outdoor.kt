package com.onehitwonders.startpage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Outdoor(
    @PrimaryKey(autoGenerate = true)
    val idOutdoor: Int,
    val idEstacionamento: Int,
    val latitude: Double,
    val longitude: Double
)