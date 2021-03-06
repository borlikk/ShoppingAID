package com.onehitwonders.startpage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Loja(
    @PrimaryKey(autoGenerate = true)
    val idLoja: Int,
    val nomeLoja: String,
    val idShopping: Int,
    val pisoLoja: Int,
    val lotacaoLoja: Int,
    val website: String,
    val email: String,
    val contacto: String,
    val horarioLoja: String
)