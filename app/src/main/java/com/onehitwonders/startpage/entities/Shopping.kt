package com.onehitwonders.startpage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Shopping(
    @PrimaryKey(autoGenerate = true)
    val idShopping: Int,
    val name: String,
    val location: String,
    val horarioShopping: String,
    val lotacaoShopping: Int,
    val website: String
)