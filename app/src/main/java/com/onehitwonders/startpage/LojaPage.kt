package com.onehitwonders.startpage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.storepage.*
import kotlinx.coroutines.launch

class LojaPage : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.storepage)

        val nomeShopping = intent.getStringExtra("nomeShopping")
        val nomeLoja = intent.getStringExtra("nomeLoja")
        val dao = ShoppingDatabase.getInstance(this).shoppingDao

        storename.text = nomeLoja
        banner.setImageResource(R.drawable.store)

        lifecycleScope.launch {
            val idShopping = dao.searchShopping(nomeShopping)
            val infoLoja = dao.loja(idShopping.first().idShopping, nomeLoja)

            horarioLoja.text = infoLoja.first().horarioLoja
            emailLoja.text = infoLoja.first().email
            pisoLoja_storePage.text = infoLoja.first().pisoLoja.toString()
            contactoLoja.text = infoLoja.first().contacto
            website.text = infoLoja.first().website

        }
    }

}