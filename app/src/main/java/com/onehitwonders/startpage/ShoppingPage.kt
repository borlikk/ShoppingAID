package com.onehitwonders.startpage

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.shoppinginfo.*

class ShoppingPage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.shoppinginfo)

        val exampleList = generateDummyList(500)

        store_recycler.adapter = AdapterRecycler(exampleList)
        store_recycler.layoutManager = LinearLayoutManager(this)
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


}