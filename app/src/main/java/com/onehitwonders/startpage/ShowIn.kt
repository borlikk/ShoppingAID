package com.onehitwonders.startpage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.show_in.*
import kotlinx.coroutines.launch

class ShowIn: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_in)

        val dao = ShoppingDatabase.getInstance(this).shoppingDao
        lifecycleScope.launch {
            var ultimoIn = dao.searchIn()

            floorInfo.text = ultimoIn.first().piso
            sectionInfo.text = ultimoIn.first().seccao
            numberInfo.text = ultimoIn.first().numero
        }

        goToLanding.setOnClickListener{
            startActivity(Intent(this, Landing::class.java))
        }
    }
}