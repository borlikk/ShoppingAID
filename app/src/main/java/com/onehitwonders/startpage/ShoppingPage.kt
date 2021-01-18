package com.onehitwonders.startpage

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.shoppinginfo.*

class ShoppingPage: AppCompatActivity(), AdapterRecycler.OnItemClickListener {
    private val exampleList = generateDummyList(45)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shoppinginfo)



        store_recycler.adapter = AdapterRecycler(exampleList, this)
        store_recycler.layoutManager = GridLayoutManager(this, 2)
        store_recycler.setHasFixedSize(true)
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