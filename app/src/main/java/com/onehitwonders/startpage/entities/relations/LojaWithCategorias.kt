package com.onehitwonders.startpage.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.onehitwonders.startpage.entities.Categoria
import com.onehitwonders.startpage.entities.Loja

data class LojaWithCategorias(
    @Embedded val nomeLoja: Loja,
    @Relation(
        parentColumn = "nomeLoja",
        entityColumn = "nomeLoja"
    )
    val categorias: List<Categoria>
)