package com.onehitwonders.startpage

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEARCH
import android.content.Intent.FILL_IN_PACKAGE
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import kotlinx.android.synthetic.main.campreview.*
import java.util.*
import java.util.jar.Manifest

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
                    yikesbro.text = it.text
                }
            }
            errorCallback = ErrorCallback {
                runOnUiThread{
                    Log.e("Main", "Camera Initialization Error: ${it.message}")
                }
            }
        }

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

