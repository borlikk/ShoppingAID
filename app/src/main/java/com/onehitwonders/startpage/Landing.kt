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
import android.os.PersistableBundle
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.campreview.*
import java.util.*
import java.util.jar.Manifest


class Landing : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        /**
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        val status = databaseHandler.dataBaseInserts()

        if (status) {
            Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
        }
        **/

        setContentView(R.layout.landing)
        val camera = findViewById<FloatingActionButton>(R.id.btncamera)
        camera.setOnClickListener {
            startActivity(Intent(this, CameraPreview::class.java))
        }
        val shopmain = findViewById<FloatingActionButton>(R.id.btnmain)
        shopmain.setOnClickListener{
            startActivity(Intent(this, ShoppingHomepage::class.java))
        }
    }
}
