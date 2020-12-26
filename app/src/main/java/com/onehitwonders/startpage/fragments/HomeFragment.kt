package com.onehitwonders.startpage.fragments

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import com.onehitwonders.startpage.DatabaseHandler
import com.onehitwonders.startpage.R

class HomeFragment : Fragment() {

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
        val listview = view.findViewById<ListView>(R.id.list)

        var adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, arrayList) }
        listview.adapter = adapter

        search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val databaseHandler by lazy { DatabaseHandler(context!!) }

                var result: Cursor?
                result = databaseHandler.searchShopping(p0)

                if(result.moveToFirst()){
                    while (result.moveToNext()) {
                        val add2 = result.getString(0)
                        arrayList.add(add2)
                    }

                }

                if (arrayList.contains(p0)){
                    adapter?.filter?.filter(p0)
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter?.filter?.filter(p0)
                return false
            }

        })
    }
    }
