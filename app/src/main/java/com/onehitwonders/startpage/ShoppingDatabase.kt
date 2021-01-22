package com.onehitwonders.startpage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onehitwonders.startpage.entities.*

@Database(
    entities = [
        Shopping::class,
        Loja::class,
        Categoria::class,
        Servico::class,
        Estacionamento::class,
        Outdoor::class,
        Indoor::class
    ],
    version = 2
)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract val shoppingDao: ShoppingDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingDatabase? = null

        fun getInstance(context: Context): ShoppingDatabase{
            synchronized(this){
                return INSTANCE?: Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingDatabase::class.java,
                    "shopping_db"
                ).fallbackToDestructiveMigration().build().also {
                    INSTANCE = it
                }
            }
        }
    }
}