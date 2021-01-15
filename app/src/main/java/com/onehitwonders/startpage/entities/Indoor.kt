package com.onehitwonders.startpage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Indoor(
    @PrimaryKey(autoGenerate = true)
    val idIndoor: Int,
    val idEstacionamento: Int,
    val piso: Int,
    val seccao: String,
    val numero: Int
)