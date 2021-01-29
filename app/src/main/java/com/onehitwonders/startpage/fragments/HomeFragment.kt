package com.onehitwonders.startpage.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.onehitwonders.startpage.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.shoppinginfo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment() : Fragment() , LojaAdapter.OnItemClickListener{

    private val listLoja = ArrayList<LojaItem>()
    private val displayList = ArrayList<LojaItem>()
    private var nome: String = ""

    private val scanCodeViewModel by lazy {
        activity?.let { ViewModelProviders.of(it).get(ScanCodeViewModel::class.java) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val search = view.findViewById<SearchView>(R.id.barraPesquisa)

        val dao by lazy { ShoppingDatabase.getInstance(requireContext()).shoppingDao }

        if (listLoja.isEmpty()) {
            lifecycleScope.launch {
                val listLojas = scanCodeViewModel?.scanCode?.let { dao.searchLojas(it.toInt()) }

                if (listLojas != null) {
                    for (loja in listLojas) {
                        val exemploLoja = LojaItem(
                            R.drawable.store_list,
                            loja.nomeLoja,
                            loja.pisoLoja.toString()
                        )
                        listLoja.add(exemploLoja)
                    }
                }

                displayList.addAll(listLoja)

            }
        }

        shopRecyclerView.adapter = LojaAdapter(displayList, this)
        shopRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        shopRecyclerView.setHasFixedSize(false)


        search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {


                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                if (p0!!.isNotBlank()){
                    displayList.clear()
                    val search = p0.toLowerCase(Locale.getDefault())
                    listLoja.forEach {
                        if (it.text1.toLowerCase(Locale.getDefault()).contains(search)){
                            displayList.add(it)
                        }
                    }

                    shopRecyclerView.adapter!!.notifyDataSetChanged()
                }else{
                    displayList.clear()
                    displayList.addAll(listLoja)
                    shopRecyclerView.adapter!!.notifyDataSetChanged()
                }
                return true
            }

        })
    }

    override fun onItemClick(position: Int) {
        val clickedLoja = displayList[position]

        val dao by lazy { ShoppingDatabase.getInstance(requireContext()).shoppingDao }

        lifecycleScope.launch {
            val shopping = scanCodeViewModel?.scanCode?.let { dao.map(it.toInt()) }

            if (shopping?.isEmpty() == false) {
                nome = shopping.first().name
                //Toast.makeText(activity, nome, Toast.LENGTH_LONG).show()

                val intentLoja = Intent(this@HomeFragment.context, LojaPage::class.java)
                intentLoja.putExtra("nomeLoja", clickedLoja.text1)
                intentLoja.putExtra("nomeShopping", nome)
                startActivity(intentLoja)
            }
        }


    }

}
