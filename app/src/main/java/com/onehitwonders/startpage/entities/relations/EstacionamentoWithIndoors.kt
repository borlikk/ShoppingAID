package com.onehitwonders.startpage.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.onehitwonders.startpage.entities.Estacionamento
import com.onehitwonders.startpage.entities.Indoor

data class EstacionamentoWithIndoors(
    @Embedded val idEstacionamento: Estacionamento,
    @Relation(
        parentColumn = "idEstacionamento",
        entityColumn = "idEstacionamento"
    )
    val indoors: List<Indoor>
)