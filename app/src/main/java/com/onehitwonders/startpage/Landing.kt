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
import androidx.lifecycle.lifecycleScope
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.onehitwonders.startpage.entities.*
import kotlinx.android.synthetic.main.campreview.*
import kotlinx.android.synthetic.main.landing.*
import kotlinx.coroutines.launch
import java.util.*
import java.util.jar.Manifest


class Landing : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val dao = ShoppingDatabase.getInstance(this).shoppingDao


        /**
        val shoppings = listOf(
            Shopping(0, "GaiaShopping", "Gaia", "10:00 às 22:30", 600, "https://www.gaiashopping.pt/mapa"),
            Shopping(0, "ArrabidaShopping", "Arrabida", "10:00 às 22:30", 1000, "https://www.arrabidashopping.com/mapa")
        )

        val lojas = listOf(
            Loja("Sport Zone", 1, 0, 15, "https://www.sportzone.pt", "Loja de produtos de Desporto", "Seg a Qua: 10:00 às 22:00 Dom, Sáb: 10:00 às 13:00 Qui: 10:00 às 19:00"),
            Loja("FNAC", 1, 1, 15, "http://www.fnac.pt", "Com 360.000 referências de artigos, entre as quais livros (160.000), música (119.000), filmes (15.000), som e imagem (10.000), telecomunicações e informática (10.000) e jogos (12.000), a Fnac dispõe de uma oferta inigualável em produtos culturais e tecnológicos. Além da escolha de produtos, a Fnac oferece inúmeros serviços como a encomenda de livros e discos, as entregas ao domicílio ou o serviço pós-venda. No apoio à Fnac dispõe ainda de forum e espaço fotográfico.", "Seg a Sex: 10:00 às 22:30 Dom, Sáb: 10:00 às 13:00")
        )

        val categorias = listOf(
            Categoria(0, "Sport Zone", "Desporto"),
            Categoria(0, "Sport Zone", "Roupa"),
            Categoria(0, "FNAC", "Tecnologia"),
            Categoria(0, "FNAC", "Livros")
        )

        val servicos = listOf(
            Servico(0, 1, 1, "ATM"),
            Servico(0, 1, 0, "Balcão Central"),
            Servico(0, 2, 1, "ATM"),
            Servico(0, 2, 0, "Balcão Central")
        )

        val estacionamentos = listOf(
            Estacionamento(0, 1, 0),
            Estacionamento(0, 1, 1)
        )

        val outdoor = listOf(
            Outdoor(0, 1, 2.23123, 1.123153)
        )

        val indoor = listOf(
            Indoor(0, 2, -1, "B", 10)
        )

        lifecycleScope.launch{
            shoppings.forEach{ dao.insertShopping(it)}
            lojas.forEach { dao.insertLoja(it) }
            categorias.forEach { dao.insertCategoria(it) }
            servicos.forEach { dao.insertServico(it) }
            estacionamentos.forEach { dao.insertEstacionamento(it) }
            outdoor.forEach { dao.insertOutdoor(it) }
            indoor.forEach { dao.insertIndoor(it) }
        }
        **/

        val images = listOf(
            R.drawable.ass,
            R.drawable.monke
        )

        setContentView(R.layout.landing)

        val adapter = viewPagerAdapter(images)
        viewPager.adapter = adapter

        val camera = findViewById<FloatingActionButton>(R.id.btncamera)
        camera.setOnClickListener {
            startActivity(Intent(this, CameraPreview::class.java))
        }
        val shopmain = findViewById<FloatingActionButton>(R.id.btnmain)
        shopmain.setOnClickListener{
            startActivity(Intent(this, SearchShopping::class.java))
        }

    }

}
