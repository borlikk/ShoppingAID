package com.onehitwonders.startpage


import android.content.ContentValues
import android.content.Context
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

        private val KEY_ID_LOJA = "IdLoja"
        private val KEY_NOME_LOJA = "nomeLoja"
        private val KEY_PISO = "piso"
        private val KEY_LOTACAO = "lotacao"
        private val KEY_WEBSITE = "website"
        private val KEY_DESCRICAO = "descricao"
        private val KEY_HORARIO = "horario"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_SHOPPING_TABLE = ("CREATE TABLE " + TABLE_SHOPPINGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT, "
                + KEY_LOCATION + " TEXT)")
        db?.execSQL(CREATE_SHOPPING_TABLE)

        val CREATE_LOJA_TABLE = ("CREATE TABLE " + TABLE_LOJAS + "("
                + KEY_ID_LOJA + " INTEGER PRIMARY KEY," + KEY_NOME_LOJA + " TEXT,"
                + KEY_PISO + " TEXT," + KEY_LOTACAO + " INTEGER, " + KEY_WEBSITE + " TEXT, " + KEY_DESCRICAO + " TEXT," + KEY_HORARIO + " TEXT)")
        db?.execSQL(CREATE_LOJA_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_SHOPPINGS")
        onCreate(db)
    }

    fun addShopping(shopping: ShoppingClass): Long? {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, shopping.name) // ShoppingInfoClass Name
        contentValues.put(KEY_LOCATION, shopping.location) // ShoppingInfoClass Location

        // Inserting employee details using insert query.
        val insert = db.insert(TABLE_SHOPPINGS, null, contentValues)

        return insert
    }

}
