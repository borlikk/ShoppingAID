package com.onehitwonders.startpage

import androidx.room.*
import com.onehitwonders.startpage.entities.*
import com.onehitwonders.startpage.entities.relations.*

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertShopping(shopping: Shopping)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertLoja(loja: Loja)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertCategoria(categoria: Categoria)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertServico(servico: Servico)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertEstacionamento(estacionamento: Estacionamento)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertOutdoor(outdoor: Outdoor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertIndoor(indoor: Indoor)

    @Transaction
    @Query("SELECT * FROM Shopping WHERE idShopping = :idShopping")
    suspend fun getShoppingWithLojas(idShopping: Int): List<ShoppingWithLojas>

    @Transaction
    @Query("SELECT * FROM Loja WHERE nomeLoja = :nomeLoja")
    suspend fun getLojaWithCategorias(nomeLoja: String): List<LojaWithCategorias>

    @Transaction
    @Query("SELECT * FROM Shopping WHERE idShopping = :idShopping")
    suspend fun getShoppingWithServicos(idShopping: Int): List<ShoppingWithServicos>

    @Transaction
    @Query("SELECT * FROM Shopping WHERE idShopping = :idShopping")
    suspend fun getShoppingWithEstacionamentos(idShopping: Int): List<ShoppingWithEstacionamentos>

    @Transaction
    @Query("SELECT * FROM Estacionamento WHERE idEstacionamento = :idEstacionamento")
    suspend fun getShoppingWithOutdoor(idEstacionamento: Int): List<EstacionamentoWithOutdoors>

    @Transaction
    @Query("SELECT * FROM Estacionamento WHERE idEstacionamento = :idEstacionamento")
    suspend fun getShoppingWithIndoor(idEstacionamento: Int): List<EstacionamentoWithIndoors>

    @Transaction
    @Query("SELECT * FROM Shopping WHERE name = :name")
    suspend fun searchShopping(name: String?): List<Shopping>

    @Transaction
    @Query("SELECT * FROM Loja WHERE idShopping = :id")
    suspend fun searchLojas(id: Int): List<Loja>
}