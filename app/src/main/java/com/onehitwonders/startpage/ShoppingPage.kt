package com.onehitwonders.startpage

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.shoppinginfo.*
import kotlinx.android.synthetic.main.shoppinginfo.imageView2
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ShoppingPage: AppCompatActivity(), AdapterRecycler.OnItemClickListener, OnMapReadyCallback{
    private val listLoja = ArrayList<RecyclerItem>()
    private val displayList = ArrayList<RecyclerItem>()
    private lateinit var mapView: MapView

    lateinit var nomeShopping: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shoppinginfo)
        val dao = ShoppingDatabase.getInstance(this).shoppingDao

        nomeShopping = intent.getStringExtra("nomeShopping").toString()

        val mapViewBundle = savedInstanceState?.getBundle("MAPVIEW_BUNDLE_KEY")
        mapView = mapa
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)

        lifecycleScope.launch {
            val listShopping = dao.searchShopping(nomeShopping)
            shoppingname.text = listShopping.first().name
            shoppinghorario2.text = listShopping.first().horarioShopping

            /**
            if (shopping.first().name == "GaiaShopping"){
            imageView2.setImageResource(R.drawable.web_gaia)
            }else if (shopping.first().name == "ArrabidaShopping"){
            imageView2.setImageResource(R.drawable.arrabida)
            }else if (shopping.first().name == "NorteShopping"){
            imageView2.setImageResource(R.drawable.norte)
            }else if (shopping.first().name == "8ª Avenida"){
            imageView2.setImageResource(R.drawable.avenida)
            }else if (shopping.first().name == "Colombo"){
            imageView2.setImageResource(R.drawable.colombo)
            }
             **/

            when(listShopping.first().name){
                "GaiaShopping" -> imageView2.setImageResource(R.drawable.web_gaia)
                "ArrabidaShopping" -> imageView2.setImageResource(R.drawable.arrabida)
                "NorteShopping" -> imageView2.setImageResource(R.drawable.norte)
                "8ª Avenida" -> imageView2.setImageResource(R.drawable.avenida)
                "Colombo" -> imageView2.setImageResource(R.drawable.colombo)
            }

            val listLojas = dao.searchLojas(listShopping.first().idShopping)

            for (loja in listLojas) {
                val exemploLoja = RecyclerItem(R.drawable.store_list, loja.nomeLoja)
                listLoja.add(exemploLoja)
            }

            displayList.addAll(listLoja)

        }

        store_recycler.adapter = AdapterRecycler(displayList, this)
        store_recycler.layoutManager = GridLayoutManager(this, 2)
        store_recycler.setHasFixedSize(false)


        search2?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

                    store_recycler.adapter!!.notifyDataSetChanged()
                }else{
                    displayList.clear()
                    displayList.addAll(listLoja)
                    store_recycler.adapter!!.notifyDataSetChanged()
                }
                return true
            }

        })

    }

    override fun onItemClick(position: Int) {
        val clickedLoja = displayList[position]
        //Toast.makeText(this, "Loja: ${clickedLoja.text1}", Toast.LENGTH_LONG).show()

        val intentLoja = Intent(this, LojaPage::class.java)
        intentLoja.putExtra("nomeLoja", clickedLoja.text1)
        intentLoja.putExtra("nomeShopping", intent.getStringExtra("nomeShopping"))
        startActivity(intentLoja)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val mapViewBundle = outState.getBundle("MAPVIEW_BUNDLE_KEY") ?: Bundle().also {
            outState.putBundle("MAPVIEW_BUNDLE_KEY", it)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(p0: GoogleMap?) {
        val dao = ShoppingDatabase.getInstance(this).shoppingDao
        var latitude: Double
        var longitude: Double

        lifecycleScope.launch {
            val listShopping = dao.searchShopping(nomeShopping)

            latitude = listShopping.first().locationlat
            longitude = listShopping.first().locationlon

            p0?.addMarker(MarkerOptions().position(LatLng(latitude, longitude)).title(listShopping.first().name))
            p0?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), 14.0f))
        }

    }



}