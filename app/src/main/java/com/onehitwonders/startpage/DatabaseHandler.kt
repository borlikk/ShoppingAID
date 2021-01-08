package com.onehitwonders.startpage


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "ShoppingDataBase"

        //Tabela dos shoppings
        private val TABLE_SHOPPINGS = "ShoppingTable"

        private val KEY_ID = "IdShopping"
        private val KEY_NAME = "name"
        private val KEY_LOCATION = "location"

        //tabela das lojas
        private val TABLE_LOJAS = "LojasTable"

        private val FKEY_ID_SHOPPING = "IdShopping"
        private val KEY_ID_LOJA = "IdLoja"
        private val KEY_NOME_LOJA = "nomeLoja"
        private val KEY_PISO = "piso"
        private val KEY_LOTACAO = "lotacao"
        private val KEY_WEBSITE = "website"
        private val KEY_DESCRICAO = "descricao"
        private val KEY_HORARIO = "horario"

        //Tabela das Categorias de cada Loja
        private val TABLE_CATEGORIAS = "Categorias"

        private val KEY_ID_CATEGORIA = "IdCategoria"
        private val FKEY_NOME_LOJA = "NomeLoja"
        private val KEY_CATEGORIA = "Categoria"

        //Tabela de Serviços de cada Shopping
        private val TABLE_SERVICOS = "Servicos"

        private val KEY_ID_SERVICO = "idServico"
        private val FKEY_ID_SHOPPING_deservicos = "idShopping"
        private val KEY_TIPO_SERVICO = "tipo"
        private val KEY_PISO_SERVICO = "piso"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_SHOPPING_TABLE = ("CREATE TABLE " + TABLE_SHOPPINGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT, "
                + KEY_LOCATION + " TEXT)")
        db?.execSQL(CREATE_SHOPPING_TABLE)

        val CREATE_LOJA_TABLE = ("CREATE TABLE " + TABLE_LOJAS + "("
                + KEY_ID_LOJA + " INTEGER PRIMARY KEY," + FKEY_ID_SHOPPING + " INTEGER," + KEY_NOME_LOJA + " TEXT,"
                + KEY_PISO + " TEXT," + KEY_LOTACAO + " INTEGER, " + KEY_WEBSITE + " TEXT, " + KEY_DESCRICAO + " TEXT," + KEY_HORARIO + " TEXT)")
        db?.execSQL(CREATE_LOJA_TABLE)

        val CREATE_CATEGORIA_TABLE = ("CREATE TABLE " + TABLE_CATEGORIAS + " ("
                + KEY_ID_CATEGORIA + " INTEGER PRIMARY KEY, " + FKEY_NOME_LOJA + " TEXT, " + KEY_CATEGORIA + " TEXT)")

        db?.execSQL(CREATE_CATEGORIA_TABLE)

        val CREATE_SERVICOS_TABLE = ("CREATE TABLE " + TABLE_SERVICOS + " ("
                + KEY_ID_SERVICO + " INTEGER PRIMARY KEY," + FKEY_ID_SHOPPING_deservicos + " INTEGER, " + KEY_TIPO_SERVICO + " TEXT, " + KEY_PISO_SERVICO + " TEXT)")

        db?.execSQL(CREATE_SERVICOS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_SHOPPINGS")
        onCreate(db)
    }

    fun dataBaseInserts(): Boolean {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, "GayaShopping") // ShoppingInfoClass Name
        contentValues.put(KEY_LOCATION, "Gaia") // ShoppingInfoClass Location

        val insert = db.insert(TABLE_SHOPPINGS, null, contentValues)

        contentValues.put(KEY_NAME, "GayaShopping2")
        contentValues.put(KEY_LOCATION, "Gaya")

        val insert2 = db.insert(TABLE_SHOPPINGS, null, contentValues)

        val contentValuesServico = ContentValues()
        contentValuesServico.put(FKEY_ID_SHOPPING_deservicos, 1)

        val contentValuesLojas = ContentValues()
        contentValuesLojas.put(FKEY_ID_SHOPPING, 1)
        contentValuesLojas.put(KEY_NOME_LOJA, "Sport Zone")
        contentValuesLojas.put(KEY_PISO, 0)
        contentValuesLojas.put(KEY_LOTACAO, 25)
        contentValuesLojas.put(KEY_WEBSITE, "https://www.sportzone.pt/")
        contentValuesLojas.put(KEY_DESCRICAO, "Loja de produtos de Desporto")
        contentValuesLojas.put(KEY_HORARIO, "Seg a Qua: 10:00 às 22:00 Dom, Sáb: 10:00 às 13:00 Qui: 10:00 às 19:00")

        val insertSport = db.insert(TABLE_LOJAS, null, contentValuesLojas)

        contentValuesLojas.put(FKEY_ID_SHOPPING, 1)
        contentValuesLojas.put(KEY_NOME_LOJA, "FNAC")
        contentValuesLojas.put(KEY_PISO, 1)
        contentValuesLojas.put(KEY_LOTACAO, 25)
        contentValuesLojas.put(KEY_WEBSITE, "http://www.fnac.pt")
        contentValuesLojas.put(KEY_DESCRICAO, "Com 360.000 referências de artigos, entre as quais livros (160.000), música (119.000), filmes (15.000), som e imagem (10.000), telecomunicações e informática (10.000) e jogos (12.000), a Fnac dispõe de uma oferta inigualável em produtos culturais e tecnológicos. Além da escolha de produtos, a Fnac oferece inúmeros serviços como a encomenda de livros e discos, as entregas ao domicílio ou o serviço pós-venda. No apoio à Fnac dispõe ainda de forum e espaço fotográfico.")
        contentValuesLojas.put(KEY_HORARIO, "Seg a Sex: 10:00 às 22:30 Dom, Sáb: 10:00 às 13:00")

        val insertFNAC = db.insert(TABLE_LOJAS, null, contentValuesLojas)

        val contentValuesCategoria = ContentValues()
        contentValuesCategoria.put(FKEY_NOME_LOJA, "SportZone")
        contentValuesCategoria.put(KEY_CATEGORIA, "Desporto")

        db.insert(TABLE_CATEGORIAS, null, contentValuesCategoria)

        contentValuesCategoria.put(FKEY_NOME_LOJA, "SportZone")
        contentValuesCategoria.put(KEY_CATEGORIA, "Roupa")

        db.insert(TABLE_CATEGORIAS, null, contentValuesCategoria)

        contentValuesCategoria.put(FKEY_NOME_LOJA, "FNAC")
        contentValuesCategoria.put(KEY_CATEGORIA, "Tecnologia")

        db.insert(TABLE_CATEGORIAS, null, contentValuesCategoria)

        contentValuesCategoria.put(FKEY_NOME_LOJA, "FNAC")
        contentValuesCategoria.put(KEY_CATEGORIA, "Livros")

        db.insert(TABLE_CATEGORIAS, null, contentValuesCategoria)

        return insert != null && insertSport != null && insertFNAC != null
    }

    fun searchShopping(shopping: String?): Cursor {
        val db = this.readableDatabase

        val searchQuery = ("SELECT " + KEY_NAME + " FROM " + TABLE_SHOPPINGS + " WHERE " + KEY_NAME + " LIKE '" + shopping + "%'")

        return db.rawQuery(searchQuery, null)
    }

}
