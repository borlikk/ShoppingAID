package com.onehitwonders.startpage

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.onehitwonders.startpage.entities.Estacionamento
import com.onehitwonders.startpage.entities.Indoor
import com.onehitwonders.startpage.entities.Outdoor
import kotlinx.android.synthetic.main.park_outdoor.*
import kotlinx.coroutines.launch

private const val LOCATION_REQUEST_CODE = 101
class ParkOutdoor: AppCompatActivity() {

    private var dao = ShoppingDatabase.getInstance(this).shoppingDao
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.park_outdoor)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getLocation.setOnClickListener {
            setupPermissions()

            startActivity(Intent(this, Landing::class.java))
        }
    }

    private fun checkPermission() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }else{
            getLocation()
        }
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)

        if (permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
        getLocation()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationProviderClient.lastLocation?.addOnSuccessListener {
            if (it == null){
                Toast.makeText(this, "Sorry Cant Get Location", Toast.LENGTH_SHORT).show()
            }else it.apply{
                val latitudeInfo = it.latitude
                val longitudeInfo = it.longitude

                var numPark = "Forgot to Input"
                lifecycleScope.launch {
                    val estacionamento = Estacionamento(0, 1, 0)
                    dao.insertEstacionamento(estacionamento)

                    val ultimoEstacionamento = dao.searchEstacionamento()

                    val estacionamentoOut = Outdoor(0, ultimoEstacionamento.first().idEstacionamento, latitudeInfo, longitudeInfo)
                    dao.insertOutdoor(estacionamentoOut)

                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            LOCATION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You need Location Permission to get your location!", Toast.LENGTH_LONG).show()
                }
                else {
                    //success
                }
            }
        }
    }
}