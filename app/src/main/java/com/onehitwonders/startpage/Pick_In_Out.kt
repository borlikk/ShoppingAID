package com.onehitwonders.startpage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.pick_in_out.*
import kotlinx.coroutines.launch

class Pick_In_Out : AppCompatActivity(){

    private var check: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pick_in_out)

        val dao = ShoppingDatabase.getInstance(this).shoppingDao
        lifecycleScope.launch {
            var ultimoEstacionamento = dao.searchEstacionamento()
            if (ultimoEstacionamento.first().tipoEstacionamento == 0) {
                check = 0
            } else {
                check = 1
            }
            ultimoEstacionamento = emptyList()
        }


        val ind = indoor
        ind.setOnClickListener{
            startActivity(Intent(this, ParkIndoor::class.java))
        }

        outdoor.setOnClickListener {
            startActivity(Intent(this, ParkOutdoor::class.java))
        }

        lastPark.setOnClickListener {
            val dao = ShoppingDatabase.getInstance(this).shoppingDao
            lifecycleScope.launch {
                var ultimoEstacionamento = dao.searchEstacionamento()
                if (ultimoEstacionamento.first().tipoEstacionamento == 0) {
                    check = 0
                } else {
                    check = 1
                }
                ultimoEstacionamento = emptyList()
            }

            if (check == 0){
                startActivity(Intent(this, ShowOut::class.java))
            }else{
                startActivity(Intent(this, ShowIn::class.java))
            }
        }
    }
}