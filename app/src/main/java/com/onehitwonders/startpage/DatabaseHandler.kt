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
        private val KEY_HORARIO_SHOPPING = "horario"
        private val KEY_LOTACAO_SHOPPING = "lotacao"

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

        //Tabela de estacionamentos
        private val TABLE_ESTACIONAMENTOS = "Estacionamentos"

        private val KEY_ID_ESTACIONAMENTO = "isEstacionamento"
        private val FKEY_IDSHOPPING = "idShopping"
        private val KEY_TIPO_ESTACIONAMENTO = "tipo" // 0 == estacionamento Outdoor, 1 == estacionamento Indoor

        //Tabela de estacionamento Outdoor
        private val TABLE_OUTDOOR = "Outdoor"

        private val KEY_ID_OUTDOOR = "idOutdoor"
        private val FKEY_ID_ESTACIONAMENTO = "idEstacionamento"
        private val KEY_LATITUDE = "latitude"
        private val KEY_LONGITUDE = "longitude"

        //Tabela de estacionamento InDoor
        private val TABLE_INDOOR = "InDoor"

        private val KEY_ID_INDOOR = "idInDoor"
        private val FKEY_IDESTACIONAMENTO = "idEstacionamento"
        private val KEY_PISO_ESTACIONAMENTO = "piso"
        private val KEY_SECCAO = "seccao"
        private val KEY_NUMERO = "numero"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_SHOPPING_TABLE = ("CREATE TABLE " + TABLE_SHOPPINGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT, "
                + KEY_LOCATION + " TEXT, " + KEY_HORARIO_SHOPPING + " TEXT, " + KEY_LOTACAO_SHOPPING + " INTEGER)")
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

        val CREATE_ESTACIONAMENTO_TABLE = ("CREATE TABLE " + TABLE_ESTACIONAMENTOS + " ("
                + KEY_ID_ESTACIONAMENTO + " INTEGER PRIMARY KEY, " + FKEY_IDSHOPPING + " INTEGER, " + KEY_TIPO_ESTACIONAMENTO + " BIT)")
        db?.execSQL(CREATE_ESTACIONAMENTO_TABLE)

        val CREATE_OUTDOOR_TABLE = ("CREATE TABLE " + TABLE_OUTDOOR + " ("
                + KEY_ID_OUTDOOR + " INTEGER PRIMARY KEY, " + FKEY_ID_ESTACIONAMENTO + " INTEGER, " + KEY_LATITUDE + " DOUBLE, " + KEY_LONGITUDE + " DOUBLE)")
        db?.execSQL(CREATE_OUTDOOR_TABLE)

        val CREATE_INDOOR_TABLE = ("CREATE TABLE " + TABLE_INDOOR + "("
                + KEY_ID_INDOOR + " INTEGER PRIMARY KEY, " + FKEY_IDSHOPPING + " INTEGER, " + KEY_PISO_ESTACIONAMENTO + " INTEGER, "+ KEY_SECCAO + " CHARACTER(1), " + KEY_NUMERO + " INTEGER)")
        db?.execSQL(CREATE_INDOOR_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_SHOPPINGS")
        onCreate(db)
    }

    fun dataBaseInserts(): Boolean {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, "GayaShopping") // ShoppingInfoClass Name
        contentValues.put(KEY_LOCATION, "Gaia")// ShoppingInfoClass Location
        contentValues.put(KEY_HORARIO_SHOPPING, "10:00 às 22:30")
        contentValues.put(KEY_LOTACAO_SHOPPING, 600)

        val insert = db.insert(TABLE_SHOPPINGS, null, contentValues)

        contentValues.put(KEY_NAME, "GayaShopping2")
        contentValues.put(KEY_LOCATION, "Gaya")
        contentValues.put(KEY_HORARIO_SHOPPING, "10:00 às 22:30")
        contentValues.put(KEY_LOTACAO_SHOPPING, 600)

        val insert2 = db.insert(TABLE_SHOPPINGS, null, contentValues)

        val contentValuesServico = ContentValues()
        contentValuesServico.put(FKEY_ID_SHOPPING_deservicos, 1)
        contentValuesServico.put(KEY_TIPO_SERVICO, "ATM")
        contentValuesServico.put(KEY_PISO_SERVICO, "1")

        db.insert(TABLE_SERVICOS, null, contentValuesServico)

        contentValuesServico.put(FKEY_ID_SHOPPING_deservicos, 1)
        contentValuesServico.put(KEY_TIPO_SERVICO, "Balcão Central")
        contentValuesServico.put(KEY_PISO_SERVICO, "0")

        db.insert(TABLE_SERVICOS, null, contentValuesServico)

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

        val contentValuesEstacionamento = ContentValues()
        contentValuesEstacionamento.put(FKEY_IDSHOPPING, 1)
        contentValuesEstacionamento.put(KEY_TIPO_ESTACIONAMENTO, 0)

        db.insert(TABLE_ESTACIONAMENTOS, null, contentValuesEstacionamento)

        val contentValuesOutDoor = ContentValues()
        contentValuesOutDoor.put(FKEY_ID_ESTACIONAMENTO, 1)
        contentValuesOutDoor.put(KEY_LATITUDE, 39.026448)
        contentValuesOutDoor.put(KEY_LONGITUDE, 125.755402)

        db.insert(TABLE_OUTDOOR, null, contentValuesOutDoor)

        return insert != null && insertSport != null && insertFNAC != null
    }

    fun searchShopping(shopping: String?): Cursor {
        val db = this.readableDatabase

        val searchQuery = ("SELECT " + KEY_NAME + " FROM " + TABLE_SHOPPINGS + " WHERE " + KEY_NAME + " LIKE '" + shopping + "%'")

        return db.rawQuery(searchQuery, null)
    }

}
