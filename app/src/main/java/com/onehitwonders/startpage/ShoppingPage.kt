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

class ShoppingPage: AppCompatActivity(), AdapterRecycler.OnItemClickListener {
    private val exampleList = generateDummyList(10)
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

            /**
            val listLojas = dao.searchLojas(listShopping.first().idShopping)

            for (loja in listLojas){
                val nomeLoja = RecyclerItem(R.drawable.ic_laptop, loja.nomeLoja)
                listLoja += nomeLoja
            }
            **/
        }

        store_recycler.adapter = AdapterRecycler(exampleList, this)
        store_recycler.layoutManager = GridLayoutManager(this, 2)
        store_recycler.setHasFixedSize(false)

    }

    private fun generateDummyList(size: Int): List<RecyclerItem> {
        val list = ArrayList<RecyclerItem>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_kayaking_
                1 -> R.drawable.ic_laptop
                else -> R.drawable.ic_contacts
            }

            val item = RecyclerItem(drawable, "Item $i")
            list += item
        }

        return list
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = exampleList[position]
    }
}