package com.onehitwonders.startpage

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.*
import com.onehitwonders.startpage.entities.Estacionamento
import com.onehitwonders.startpage.entities.Indoor
import com.onehitwonders.startpage.entities.Outdoor
import kotlinx.android.synthetic.main.park_outdoor.*
import kotlinx.coroutines.launch
import java.util.jar.Manifest

class ParkOutdoor: AppCompatActivity() {
    var PERMISSION_CODE = 101
    private var dao = ShoppingDatabase.getInstance(this).shoppingDao
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.park_outdoor)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getLocation.setOnClickListener {
            checkForPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION, "location", PERMISSION_CODE)

            getLocation()
        }
    }

    private fun checkForPermissions(permission: String, name: String, requestCode: Int){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(this, "$name permission granted", Toast.LENGTH_SHORT).show()
                }
                shouldShowRequestPermissionRationale(permission) -> showDialog(permission, name, requestCode)
                else -> ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innerCheck(name:String){
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "$name permission refused", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "$name permission granted", Toast.LENGTH_LONG).show()
            }
        }

        when (requestCode){
            PERMISSION_CODE -> innerCheck("location")
        }
    }

    private fun showDialog(permission: String, name: String, requestCode: Int){
        val builder = AlertDialog.Builder(this)

        builder.apply {
            setMessage("Permission to access your $name is required to record your location")
            setTitle("Permission required")
            setPositiveButton("OK") { dialog, which ->
                ActivityCompat.requestPermissions(this@ParkOutdoor, arrayOf(permission), requestCode)
            }
        }
        val dialog = builder.create()
        dialog.show()

    }


    @SuppressLint("MissingPermission")
    private fun getLocation() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest,locationCallback, Looper.myLooper()
        )

        /**
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

                startActivity(Intent(this@ParkOutdoor, Landing::class.java))
            }
        }
        **/
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation = p0.lastLocation

            val latitudeInfo = lastLocation.latitude
            val longitudeInfo = lastLocation.longitude

            lifecycleScope.launch {
                val estacionamento = Estacionamento(0, 1, 0)
                dao.insertEstacionamento(estacionamento)

                val ultimoEstacionamento = dao.searchEstacionamento()

                val estacionamentoOut = Outdoor(0, ultimoEstacionamento.first().idEstacionamento, latitudeInfo, longitudeInfo)
                dao.insertOutdoor(estacionamentoOut)

            }

            startActivity(Intent(this@ParkOutdoor, Landing::class.java))
        }
    }

}