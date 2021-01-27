package com.onehitwonders.startpage
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.onehitwonders.startpage.entities.*
import kotlinx.android.synthetic.main.landing.*
import kotlinx.coroutines.launch


class Landing : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val dao = ShoppingDatabase.getInstance(this).shoppingDao

        lifecycleScope.launch{
            val checkDataBase = dao.checkDatabase()

            if (checkDataBase.isEmpty()){
                val shoppings = listOf(
                    Shopping(0, "GaiaShopping", "Gaia",41.11833833016957,-8.622343648315285,"10:00 às 22:30", 600, "https://www.gaiashopping.pt/mapa"),
                    Shopping(0, "ArrabidaShopping", "Gaia",41.14227044930134, -8.636432043805279, "10:00 às 22:30", 1000, "https://www.arrabidashopping.com/mapa")
                )

                val lojas = listOf(
                    Loja("Sport Zone", 1, 0, 15, "https://www.sportzone.pt", "gaispz2ger@sportzone.pt", "93 443 79 37 / 223780213", "Seg a Qua: 10:00 às 22:00 Dom \nSáb: 10:00 às 13:00 \nQui: 10:00 às 19:00"),
                    Loja("FNAC", 1, 1, 15, "http://www.fnac.pt/", "fnac@gaiashopping.pt", "707 313 435 | 223781041", "Seg a Sex: 10:00 às 22:30 Dom, Sáb: 10:00 às 13:00")
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
                    Indoor(0, 2, "-1", "B", "10")
                )

                shoppings.forEach{ dao.insertShopping(it)}
                lojas.forEach { dao.insertLoja(it) }
                categorias.forEach { dao.insertCategoria(it) }
                servicos.forEach { dao.insertServico(it) }
                estacionamentos.forEach { dao.insertEstacionamento(it) }
                outdoor.forEach { dao.insertOutdoor(it) }
                indoor.forEach { dao.insertIndoor(it) }
            }
        }



        val images = listOf(
            R.drawable.tutorial,
            R.drawable.tutoria_2,
            R.drawable.tutorial_3
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

        val park = park
        park.setOnClickListener{
            startActivity(Intent(this, Pick_In_Out::class.java))
        }
    }

}
