package com.onehitwonders.startpage

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.onehitwonders.startpage.entities.Estacionamento
import com.onehitwonders.startpage.entities.Indoor
import kotlinx.android.synthetic.main.park_indoor.*
import kotlinx.coroutines.launch

class ParkIndoor : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.park_indoor)

        val spinnerSec = spinnerSeccao
        val spinnerPiso = spinnerFloor
        val num = num
        val finish = finish

        ArrayAdapter.createFromResource(
            this,
            R.array.seccoes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerSec.adapter = adapter
        }

            ArrayAdapter.createFromResource(
                this,
                R.array.piso,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                spinnerPiso.adapter = adapter
        }

        finish.setOnClickListener{
            val dao = ShoppingDatabase.getInstance(this).shoppingDao
            var numPark = "Forgot to Input"
            lifecycleScope.launch {
                if (!(num.text.isEmpty())){
                    numPark = num.text.toString()
                }
                val estacionamento = Estacionamento(0, 1, 1)
                dao.insertEstacionamento(estacionamento)

                val ultimoEstacionamento = dao.searchEstacionamento()

                val estacionamentoIn = Indoor(0, ultimoEstacionamento.first().idEstacionamento, spinnerPiso.selectedItem.toString(), spinnerSec.selectedItem.toString(), numPark)
                dao.insertIndoor(estacionamentoIn)
            }

            startActivity(Intent(this, Landing::class.java))
        }

    }
}