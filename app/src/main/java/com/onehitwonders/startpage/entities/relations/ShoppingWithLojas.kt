package com.onehitwonders.startpage.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.onehitwonders.startpage.entities.Loja
import com.onehitwonders.startpage.entities.Shopping

data class ShoppingWithLojas(
    @Embedded val idShopping: Shopping,
    @Relation(
        parentColumn = "idShopping",
        entityColumn = "idShopping"
    )
    val lojas: List<Loja>
)