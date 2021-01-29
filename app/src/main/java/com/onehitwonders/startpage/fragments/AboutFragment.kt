package com.onehitwonders.startpage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.onehitwonders.startpage.R
import com.onehitwonders.startpage.ScanCodeViewModel
import com.onehitwonders.startpage.ShoppingDatabase
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_about.imageView2
import kotlinx.android.synthetic.main.shoppinginfo.*
import kotlinx.coroutines.launch

class AboutFragment(codigo: String?) : Fragment(), OnMapReadyCallback {
    private var scanCode = codigo
    private val scanCodeViewModel by lazy {
        activity?.let { ViewModelProviders.of(it).get(ScanCodeViewModel::class.java) }
    }
    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapViewBundle = savedInstanceState?.getBundle("MAPVIEW_BUNDLE_KEY")
        mapView = mapa2
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)

        val dao by lazy { ShoppingDatabase.getInstance(requireContext()).shoppingDao }

        lifecycleScope.launch {
            val shopping = dao.map(scanCode?.toInt())
            shoppingname2.text = shopping.first().name
            shoppinghorario.text = shopping.first().horarioShopping

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

            when(shopping.first().name){
                "GaiaShopping" -> imageView2.setImageResource(R.drawable.web_gaia)
                "ArrabidaShopping" -> imageView2.setImageResource(R.drawable.arrabida)
                "NorteShopping" -> imageView2.setImageResource(R.drawable.norte)
                "8ª Avenida" -> imageView2.setImageResource(R.drawable.avenida)
                "Colombo" -> imageView2.setImageResource(R.drawable.colombo)
            }

        }
        scanCodeViewModel?.scanCode = scanCode



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
        val dao by lazy { ShoppingDatabase.getInstance(requireContext()).shoppingDao }
        var latitude: Double
        var longitude: Double

        lifecycleScope.launch {
            val listShopping = dao.map(scanCode?.toInt())

            latitude = listShopping.first().locationlat
            longitude = listShopping.first().locationlon

            p0?.addMarker(MarkerOptions().position(LatLng(latitude, longitude)).title(listShopping.first().name))
            p0?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), 14.0f))
        }

    }

}