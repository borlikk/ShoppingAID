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
                    Shopping(0, "ArrabidaShopping", "Gaia",41.14227044930134, -8.636432043805279, "10:00 às 22:30", 1000, "https://www.arrabidashopping.com/mapa"),
                    Shopping(0, "NorteShopping", "Porto", 41.18065555362119, -8.655575613603808, "Mon - Sun from 10:00 to 22:30", 1000, "https://www.norteshopping.pt/mapa"),
                    Shopping(0, "8ª Avenida", "São João da Madeira",40.88891323984691, -8.492317830859164, "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00", 1650, "https://www.8avenida.com/mapa"),
                    Shopping(0, "Colombo", "Lisboa",38.75490306360611, -9.188451415578914, "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00", 1500, "https://www.colombo.pt/mapa")
                )

                val lojas = listOf(
                    Loja(0, "Ale-Hop", 1, 1, 10, "https://www.ale-hop.pt/", "baracasalehop@hotmail.com", "351 910 930 608", "Mon to Fri: 10:00 to 20:00 \nSun, Sat: 10:00 to 13:00"),
                    Loja(0, "AnaSousa", 1, 0, 5, "http://www.anasousa.pt/", "ana.sousa@gaiashopping.pt", "351 223 744 899", "Mon to Fri: 10:00 to 20:00 \nSun, Sat: 10:00 to 13:00"),
                    Loja(0, "C&A", 1, 0, 30, "http://www.c-a.com", "pedro.freitas@retail-sc.com", "351 223 791 555", "Mon to Fri: 10:00 to 20:00 \nSun, Sat: 10:00 to 13:00"),
                    Loja(0, "Calzedonia", 1, 0, 5, "http://www.calzedonia.pt", "calzedonia@gaiashopping.pt", "351 223 740 307", "Sun to Sat: 10:00 to 22:00"),
                    Loja(0, "H&M", 1, 0, 30, "http://www.hm.com/", "h.e.m@gaiashopping.pt", "351 223 716 532", "Sun to Sat: 10:00 to 22:00"),
                    Loja(0, "Parfois", 1, 0, 3, "https://www.parfois.com/pt/pt/home/", "mail@parfois.com", "351 932 264 350", "Sun to Sat: 10:00 to 22:00"),
                    Loja(0, "Sport Zone", 1, 0, 15, "https://www.sportzone.pt", "gaispz2ger@sportzone.pt", "93 443 79 37 / 223780213", "Mon, Wen: 10:00 to 22:00 Dom \nSat: 10:00 to 13:00 \nThu: 10:00 to 19:00"),
                    Loja(0, "FNAC", 1, 1, 15, "http://www.fnac.pt/", "fnac@gaiashopping.pt", "707 313 435 | 223781041", "Mon to Fri: 10:00 to 22:30 Dom \nSat: 10:00 to 13:00"),
                    Loja(0, "Bershka", 1, 0, 15, "http://www.bershka.pt/", "berhska@gaiashopping.pt", "351 223 759 005", "Sun to Sat: 10:00 to 22:00"),

                    Loja(0, "adidas", 2, 1, 10, "http://www.adidas.pt/", "adidas@arrabidashopping.com", "22 371 66 72", "Sun, Sat: 10:00 to 13:00 \nMon to Fri: 10:00 to 20:00"),
                    Loja(0, "Bershka", 2, 1, 10, "http://www.bershka.pt/", "bershka@arrabidashopping.com", "22 371 12 15", "Sun, Sat: 10:00 to 13:00 \nMon to Fri: 10:00 to 22:00"),
                    Loja(0, "Calzedonia", 2, 0, 5, "http://www.calzedonia.pt", "Not Available", "22 379 00 40", "Sun, Sat: 10:00 to 13:00 \nMon to Fri: 10:00 to 22:00"),
                    Loja(0, "F. C. Porto Store", 2, 0, 3, "Not Available", "Not Available", "22 508 33 04", "Sun, Sat: 10:00 to 13:00 \nMon to Fri: 10:00 to 22:00"),
                    Loja(0, "Opticalia", 2, 0, 5, "Not Available", "opticalia-arrabida@esonor.pt", "22 493 16 37", "Mon to Fri: 10:00 to 20:00 \nSun, Sat: 10:00 to 13:00"),
                    Loja(0, "Parfois", 2, 0, 5, "https://www.parfois.com/pt/pt/home/", "Not Available", "932 264 351", "Sun, Sat: 10:00 to 13:00 \nMon to Fri: 10:00 to 22:00"),
                    Loja(0, "Sport Zone", 2, 0, 20, "https://www.sprintersports.com/pt", "sport.zone@arrabidashopping.com", "22 374 70 65", "Sun, Sat: 10:00 to 13:00 \nMon to Fri: 10:00 to 20:00"),

                    Loja(0, "adidas", 3, 0, 20, "http://www.adidas.pt/", "pt_adidas_spcs_norteshopping@adidas.com", "22 951 83 81", "Sun to Sat: 10:00 to 22:00"),
                    Loja(0, "Bertrand", 3, 0, 10, "https://www.bertrand.pt/", "Not Available", "22 955 97 78", "Sun to Sat: 10:00 to 22:00"),
                    Loja(0, "Celeiro", 3, 0, 5, "https://www.celeiro.pt", "Not Available", "22 012 27 81", "Mon to Fri: 09:00 to 20:00 \nSat: 09:00 to 17:00 \nSun: 09:00 to 17:00"),
                    Loja(0, "chicco", 3, 0, 5, "http://www.chicco.pt", "Not Available", "21 434 79 18", "Sun to Sat: 10:00 to 22:00"),
                    Loja(0, "Cortefiel", 3, 1, 10, "http://www.cortefiel.com", "tien0540@cortefiel.com", "22 953 51 37", "Sun to Sat: 10:00 to 22:00"),
                    Loja(0, "GANT", 3, 1, 5, "http://www.gant.pt/", "Not Available", "Not Available", "Sun to Sat: 10:00 to 22:00"),
                    Loja(0, "Sport Zone", 3, 1, 25, "https://www.sprintersports.com/pt", "Not Available", "220 109 072", "Sun to Sat: 10:00 to 22:00"),
                    Loja(0, "FNAC", 3, 0, 25, "http://www.fnac.pt", "Not Available", "707 31 34 35", "Mon to Fri: 09:00 to 20:00 \nSat: 08:00 to 13:00 \nSun: 08:00 to 13:00"),
                    Loja(0, "H&M", 3, 0, 30, "http://www.hm.com/", "Not Available", "22 953 82 36", "Sun to Sat: 10:00 to 22:00"),

                    Loja(0,"Cortefiel", 4, 0, 20, "https://cortefiel.com/pt/pt", "Not Available", "256 820 010", "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00"),
                    Loja(0,"Tiffosi", 4, 0, 10, "https://www.tiffosi.com/", "Not Available", "256 878 081", "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00"),
                    Loja(0,"Worten", 4, 1, 50, "http://www.worten.pt/", "Not Available", "808 100 007", "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00"),
                    Loja(0,"Note!", 4, 0, 8, "https://noteonline.pt/", "Not Available", "Not Available", "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00"),
                    Loja(0,"Bershka", 4, 1, 30, "https://www.bershka.com/pt", "Not Available", "256 878 077", "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00"),
                    Loja(0,"Xtreme", 4, 1, 10, "https://www.xtreme.pt/", "Not Available", "Not Available", "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00"),

                    Loja(0,"Flying Tiger Copenhagen", 5, 0, 20, "https://pt.flyingtiger.com/", "Not Available", "+351 934 853 986", "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00"),
                    Loja(0,"Disney Store", 5, 0, 10, "https://www.shopdisney.pt", "Not Available", "210 170 255", "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00"),
                    Loja(0,"Brandy Melville", 5, 1, 8, "https://brandymelville.eu/", "Not Available", "21 715 19 90", "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00"),
                    Loja(0,"Farggi", 5, 1, 5, "http://www.farggi.com/", "Not Available", "966426111", "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00"),
                    Loja(0,"Billabong", 5, 2, 12, "http://eu.billabong.com/", "Not Available", "217 162 040", "Mon-Fri - 08:00-20:00 \nSat-Sun - 08:00 - 17:00")

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
