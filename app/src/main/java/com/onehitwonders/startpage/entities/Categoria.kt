package com.onehitwonders.startpage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categoria(
    @PrimaryKey(autoGenerate = true)
    val idCategoria: Int,
    val nomeLoja:String,
    val tipoCategoria: String
)