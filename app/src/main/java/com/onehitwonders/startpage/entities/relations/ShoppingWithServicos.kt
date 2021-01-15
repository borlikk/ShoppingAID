package com.onehitwonders.startpage.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.onehitwonders.startpage.entities.Loja
import com.onehitwonders.startpage.entities.Servico
import com.onehitwonders.startpage.entities.Shopping

data class ShoppingWithServicos(
    @Embedded val idShopping: Shopping,
    @Relation(
        parentColumn = "idShopping",
        entityColumn = "idShopping"
    )
    val servico: List<Servico>
)