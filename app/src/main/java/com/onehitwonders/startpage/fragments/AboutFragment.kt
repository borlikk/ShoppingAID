package com.onehitwonders.startpage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.onehitwonders.startpage.R
import com.onehitwonders.startpage.ScanCodeViewModel
import com.onehitwonders.startpage.ShoppingDatabase
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_about.imageView2
import kotlinx.android.synthetic.main.shoppinginfo.*
import kotlinx.coroutines.launch

class AboutFragment(codigo: String?) : Fragment() {
    private var scanCode = codigo
    private val scanCodeViewModel by lazy {
        activity?.let { ViewModelProviders.of(it).get(ScanCodeViewModel::class.java) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView2.setImageResource(R.drawable.web_gaia)


        val dao by lazy { ShoppingDatabase.getInstance(requireContext()).shoppingDao }

        lifecycleScope.launch {
            val shopping = dao.map(scanCode?.toInt())
            shoppingname2.text = shopping.first().name
            shoppinghorario.text = shopping.first().horarioShopping

        }
        scanCodeViewModel?.scanCode = scanCode



    }

}