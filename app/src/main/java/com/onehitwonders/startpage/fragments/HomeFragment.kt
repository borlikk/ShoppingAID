package com.onehitwonders.startpage.fragments

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.onehitwonders.startpage.R
import com.onehitwonders.startpage.ShoppingDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    companion object{
        const val ARG_NAME = "teste"

        fun newInstance(name: String): HomeFragment{
            val fragment = HomeFragment()

            val bundle = Bundle().apply {
                putString(ARG_NAME, name)
            }

            fragment.arguments = bundle

            return fragment
        }
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

        var arrayList= ArrayList<String>()
        val search = view.findViewById<SearchView>(R.id.barraPesquisa)
        /**
        val listview = view.findViewById<ListView>(R.id.list)
        val label = view.findViewById<TextView>(R.id.ScanCam)
        label.text = arguments?.getString(ARG_NAME)

        var adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, arrayList) }
        listview.adapter = adapter

        **/

        search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val dao by lazy { ShoppingDatabase.getInstance(context!!).shoppingDao }

                GlobalScope.launch {
                    val nomeShopping = dao.searchShopping(p0)
                    nomeShopping.first().name
                }

                if (arrayList.contains(p0)){
                    //adapter?.filter?.filter(p0)
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                //adapter?.filter?.filter(p0)
                return false
            }

        })
    }

    }
