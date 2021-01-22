package com.onehitwonders.startpage

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.searchshopping.*
import kotlinx.android.synthetic.main.shopping_row.*
import kotlinx.android.synthetic.main.shoppinginfo.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ShoppingPage: AppCompatActivity(){
    private val listLoja = ArrayList<RecyclerItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shoppinginfo)
        imageView2.setImageResource(R.drawable.web_gaia)
        val dao = ShoppingDatabase.getInstance(this).shoppingDao

        val nomeShopping = intent.getStringExtra("nomeShopping")

        lifecycleScope.launch {
            val listShopping = dao.searchShopping(nomeShopping)
            shoppingname.text = listShopping.first().name
            horario.text = listShopping.first().horarioShopping

            val listLojas = dao.searchLojas(listShopping.first().idShopping)

            for (loja in listLojas) {
                val exemploLoja = RecyclerItem(0, loja.nomeLoja)
                listLoja.add(exemploLoja)
            }

        }

        store_recycler.adapter = AdapterRecycler(listLoja)
        store_recycler.layoutManager = GridLayoutManager(this, 2)
        store_recycler.setHasFixedSize(false)

    }
}