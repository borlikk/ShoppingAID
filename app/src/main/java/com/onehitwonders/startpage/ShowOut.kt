package com.onehitwonders.startpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.show_in.*
import kotlinx.coroutines.launch

class ShowOut : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_out)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        var latitude: Double
        var longitude: Double
        mMap = googleMap
        val dao = ShoppingDatabase.getInstance(this).shoppingDao
        lifecycleScope.launch {
            var ultimoOut = dao.searchOut()

            latitude = ultimoOut.first().latitude
            longitude = ultimoOut.first().longitude

            // Add a marker in Sydney and move the camera

            val sydney = LatLng(latitude, longitude)
            mMap.addMarker(MarkerOptions().position(sydney).title("Your last parking"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15.0f))
        }

    }
}