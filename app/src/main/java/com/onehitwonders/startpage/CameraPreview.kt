package com.onehitwonders.startpage

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.onehitwonders.startpage.fragments.HomeFragment
import kotlinx.android.synthetic.main.campreview.*

private const val CAMERA_REQUEST_CODE = 101
class CameraPreview : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.campreview)
        setupPermissions()
        codeScanner()

    }
    private fun codeScanner() {
        var codigo: String
        codeScanner = CodeScanner(this, camprev)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false
            decodeCallback = DecodeCallback {
                runOnUiThread {
                    yikesbro.text = "Ready!"
                    codigo = it.text
                    if (yikesbro.text.toString() != "Please wait for scan"){
                        ChangePage(codigo)
                    }
                }
            }
            errorCallback = ErrorCallback {
                runOnUiThread{
                    Log.e("Main", "Camera Initialization Error: ${it.message}")
                }
            }
        }
    }

    private fun ChangePage(codigo: String) {
        val intent = Intent(this, ShoppingHomepage::class.java)
        intent.putExtra("scanCode", codigo)
        startActivity(intent)
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }
    private fun makeRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You need Camera Permission to scan the QR Code!", Toast.LENGTH_LONG).show()
                }
                else {
                    //success
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}

