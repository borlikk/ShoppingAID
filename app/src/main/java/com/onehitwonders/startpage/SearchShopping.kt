package com.onehitwonders.startpage

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class SearchShopping : AppCompatActivity(), ShoppingAdapter.OnItemClickListener{

    private val list = ArrayList<ShoppingItem>()
    private val displayList = ArrayList<ShoppingItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searchshopping)

        val search = findViewById<SearchView>(R.id.searchView)
        val recycler = findViewById<RecyclerView>(R.id.lista)

        val dao = ShoppingDatabase.getInstance(this).shoppingDao

        lifecycleScope.launch {
            val listShopping = dao.shopping()

            for (shopping in listShopping) {
                val exemploShopping = ShoppingItem(R.drawable.ic_baseline_shopping_bag_24, shopping.name, shopping.location)
                list.add(exemploShopping)
            }

            displayList.addAll(list)
            }
        recycler.adapter = ShoppingAdapter(displayList, this)
        recycler.layoutManager = GridLayoutManager(this, 1)
        recycler.setHasFixedSize(true)

        search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0!!.isNotBlank()){
                    displayList.clear()
                    val search = p0.toLowerCase(Locale.getDefault())
                    list.forEach {
                        if (it.text.toLowerCase(Locale.getDefault()).contains(search)){
                            displayList.add(it)
                        }
                    }

                    recycler.adapter!!.notifyDataSetChanged()
                }else{
                    displayList.clear()
                    displayList.addAll(list)
                    recycler.adapter!!.notifyDataSetChanged()
                }
                return true
            }

        })

    }

    override fun onItemClick(position: Int) {
        val clickedShopping = displayList[position]
        //Toast.makeText(this, "Shopping: ${clickedShopping.text}", Toast.LENGTH_LONG).show()
        val intent = Intent(this, ShoppingPage::class.java)
        intent.putExtra("nomeShopping", clickedShopping.text)
        startActivity(intent)
    }
}