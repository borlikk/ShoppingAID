package com.onehitwonders.startpage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Servico(
    @PrimaryKey(autoGenerate = true)
    val idServico: Int,
    val idShopping: Int,
    val pisoServico: Int,
    val tipoServico: String
)