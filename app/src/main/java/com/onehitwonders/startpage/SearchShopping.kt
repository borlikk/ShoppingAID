package com.onehitwonders.startpage

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SearchShopping : AppCompatActivity(), ShoppingAdapter.OnItemClickListener{
    private val adapter: ShoppingAdapter
        get() = ShoppingAdapter(list, this)
    private val list = ArrayList<ShoppingItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searchshopping)

        val search = findViewById<SearchView>(R.id.searchView)
        val recycler = findViewById<RecyclerView>(R.id.lista)

        val dao = ShoppingDatabase.getInstance(this).shoppingDao

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

        search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                lifecycleScope.launch {
                    val listShopping = dao.searchShopping(p0)
                    if (listShopping.isEmpty()) {
                        Toast.makeText(this@SearchShopping, "$p0 is not available!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@SearchShopping, SearchShopping::class.java))
                        lifecycleScope.cancel("Shopping Inexistente")
                    } else {
                        val firstItem = ShoppingItem(
                            R.drawable.ic_baseline_shopping_bag_24,
                            listShopping.first().name,
                            listShopping.first().location
                        )
                        list.removeAll(list)
                        list += firstItem
                        recycler.adapter = adapter
                    }
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                //adapter?.filter?.filter(p0)
                return false
            }

        })

    }

    override fun onItemClick(position: Int) {
        val clickedShopping = list[position]
        //Toast.makeText(this, "Shopping: ${clickedShopping.text}", Toast.LENGTH_LONG).show()
        val intent = Intent(this, ShoppingPage::class.java)
        intent.putExtra("nomeShopping", clickedShopping.text)
        startActivity(intent)
    }
}