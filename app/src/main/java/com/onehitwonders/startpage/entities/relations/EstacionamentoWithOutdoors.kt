package com.onehitwonders.startpage.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.onehitwonders.startpage.entities.Estacionamento
import com.onehitwonders.startpage.entities.Outdoor

data class EstacionamentoWithOutdoors(
    @Embedded val idEstacionamento: Estacionamento,
    @Relation(
        parentColumn = "idEstacionamento",
        entityColumn = "idEstacionamento"
    )
    val outdoors: List<Outdoor>
)