package com.onehitwonders.startpage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Indoor(
    @PrimaryKey(autoGenerate = true)
    val idIndoor: Int,
    val idEstacionamento: Int,
    val piso: String,
    val seccao: String,
    @ColumnInfo(defaultValue = "Forgot to Input") val numero: String
)