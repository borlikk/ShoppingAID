package com.onehitwonders.startpage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Estacionamento(
    @PrimaryKey(autoGenerate = true)
    val idEstacionamento: Int,
    val idShopping: Int,
    val tipoEstacionamento: Int // 0 == estacionamento Outdoor, 1 == estacionamento Indoor
)