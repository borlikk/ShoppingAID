package com.onehitwonders.startpage.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.onehitwonders.startpage.entities.Estacionamento
import com.onehitwonders.startpage.entities.Shopping

data class ShoppingWithEstacionamentos(
    @Embedded val idShopping: Shopping,
    @Relation(
        parentColumn = "idShopping",
        entityColumn = "idShopping"
    )
    val estacionamentos: List<Estacionamento>
)