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

class ParkOutdoor: AppCompatActivity() {

    private var dao = ShoppingDatabase.getInstance(this).shoppingDao
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.park_outdoor)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getLocation.setOnClickListener {
            checkPermission()

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
        if (requestCode == 1){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show()
                    getLocation()
                }else{
                    Toast.makeText(this, "Permission ddinied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}