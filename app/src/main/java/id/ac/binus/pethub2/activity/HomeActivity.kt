package id.ac.binus.pethub2.activity

import id.ac.binus.pethub2.data.AuctionProduct
import id.ac.binus.pethub2.adapter.AuctionProductAdapter
import id.ac.binus.pethub2.data.BuyProduct
import id.ac.binus.pethub2.adapter.BuyProductAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3
import id.ac.binus.pethub2.R.*
import id.ac.binus.pethub2.adapter.SlideAdapter


class HomeActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_home)

        // Slider
        val sliderImages = listOf(
            drawable.gecko1,
            drawable.gecko2,
            drawable.gecko3
        )
        val viewPagerSlider = findViewById<ViewPager2>(id.viewPagerSlider)
        viewPagerSlider.adapter = SlideAdapter(sliderImages)
        val indicator = findViewById<CircleIndicator3>(id.indicator)
        indicator.setViewPager(viewPagerSlider)

        // Menu Navigation
        val ivMenu = findViewById<ImageView>(id.ivMenu)
        val drawerLayout = findViewById<DrawerLayout>(id.drawerLayout)
        ivMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Notification
        findViewById<ImageView>(id.ivNotifications).setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }

        // Lihat Semua Lelang
        findViewById<TextView>(id.tvLihatSemuaLelang).setOnClickListener {
            startActivity(Intent(this, LelangHewanActivity::class.java))
        }

        // Lihat Semua Kategori
        findViewById<TextView>(id.tvLihatSemuaKategori).setOnClickListener {
            startActivity(Intent(this, KategoriActivity::class.java))
        }

        // Data Produk
        val buyProducts = listOf(
            BuyProduct(
                id = "1",
                name = "Leopard Gecko",
                description = "Beautiful Leopard Gecko",
                price = "Rp. 500.000",
                imageResList = listOf(drawable.gecko1, drawable.gecko2)
            ),
            BuyProduct(
                id = "2",
                name = "Bearded Dragon",
                description = "Amazing Bearded Dragon",
                price = "Rp. 750.000",
                imageResList = listOf(drawable.gecko3, drawable.gecko2)
            )
        )


        val auctionProducts = listOf(
            AuctionProduct(
                id = "1",
                name = "Ball Python",
                description = "Rare Ball Python",
                price = "Rp. 1.000.000",
                bidIncrement = "Rp. 50.000",
                imageResList = listOf(drawable.gecko1, drawable.gecko2)
            ),
            AuctionProduct(
                id = "2",
                name = "Corn Snake",
                description = "Bright Corn Snake",
                price = "Rp. 900.000",
                bidIncrement = "Rp. 40.000",
                imageResList = listOf(drawable.gecko3, drawable.gecko2)
            )
        )


        // RecyclerView Horizontal untuk Produk Lelang
        val rvLelangHewan = findViewById<RecyclerView>(id.rvLelangHewanHorizontal)
        rvLelangHewan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvLelangHewan.adapter = AuctionProductAdapter(auctionProducts)

        val rvKategoriHewan = findViewById<RecyclerView>(id.rvKategoriHewan)
        rvKategoriHewan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvKategoriHewan.adapter = BuyProductAdapter(buyProducts)



        // Padding untuk Sistem Bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main_home)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
