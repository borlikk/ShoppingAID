package com.onehitwonders.startpage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.pick_in_out.*

class Pick_In_Out : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pick_in_out)

        val ind = indoor
        ind.setOnClickListener{
            startActivity(Intent(this, ParkIndoor::class.java))
        }
    }
}