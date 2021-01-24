package com.onehitwonders.startpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.onehitwonders.startpage.fragments.AboutFragment
import com.onehitwonders.startpage.fragments.HomeFragment
import com.onehitwonders.startpage.fragments.MapFragment
import kotlinx.android.synthetic.main.activity_main.*


class ShoppingHomepage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val codigo = intent.getStringExtra("scanCode")

        val homeFragment = HomeFragment()
        val mapFragment = MapFragment()
        val aboutFragment = AboutFragment(codigo)

        makeCurrentFragment(aboutFragment)
        bot_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_map -> makeCurrentFragment(mapFragment)
                R.id.ic_about -> makeCurrentFragment(aboutFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()

        }





}

