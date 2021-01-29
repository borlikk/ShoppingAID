package com.onehitwonders.startpage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.onehitwonders.startpage.LojaItem
import com.onehitwonders.startpage.R
import com.onehitwonders.startpage.ScanCodeViewModel
import com.onehitwonders.startpage.ShoppingDatabase
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.coroutines.launch

class MapFragment () : Fragment() {

    var webmap = ""
    private val scanCodeViewModel by lazy {
        activity?.let { ViewModelProviders.of(it).get(ScanCodeViewModel::class.java) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dao by lazy { ShoppingDatabase.getInstance(requireContext()).shoppingDao }

        lifecycleScope.launch {
            val map = scanCodeViewModel?.scanCode?.let { dao.map(it.toInt()) }

            if (map != null) {
                webmap = map.first().website
            }



        }
        super.onViewCreated(view, savedInstanceState)
        val webView = view.findViewById<WebView>(R.id.mapweb)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(webmap)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true



    }
}