package com.onehitwonders.startpage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.onehitwonders.startpage.R
import com.onehitwonders.startpage.ScanCodeViewModel
import kotlinx.android.synthetic.main.fragment_about.*

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

        scanCodeViewModel?.scanCode = scanCode

        codigoInfoAbout.text = scanCode

    }

}