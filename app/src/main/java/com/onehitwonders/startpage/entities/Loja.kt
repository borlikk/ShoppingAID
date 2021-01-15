package com.onehitwonders.startpage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Loja(
    @PrimaryKey(autoGenerate = false)
    val nomeLoja: String,
    val idShopping: Int,
    val pisoLoja: Int,
    val lotacaoLoja: Int,
    val website: String,
    val descricao: String,
    val horarioLoja: String
)