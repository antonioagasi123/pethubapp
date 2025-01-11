package id.ac.binus.pethub2

import AuctionProduct
import AuctionProductAdapter
import BuyProduct
import BuyProductAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

class HomeActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Slider
        val sliderImages = listOf(
            R.drawable.gecko1,
            R.drawable.gecko2,
            R.drawable.gecko3
        )
        val viewPagerSlider = findViewById<ViewPager2>(R.id.viewPagerSlider)
        viewPagerSlider.adapter = SlideAdapter(sliderImages)
        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(viewPagerSlider)

        // Menu Navigation
        val ivMenu = findViewById<ImageView>(R.id.ivMenu)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        ivMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Notification
        findViewById<ImageView>(R.id.ivNotifications).setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }

        // Lihat Semua Lelang
        findViewById<TextView>(R.id.tvLihatSemuaLelang).setOnClickListener {
            startActivity(Intent(this, LelangHewanActivity::class.java))
        }

        // Lihat Semua Kategori
        findViewById<TextView>(R.id.tvLihatSemuaKategori).setOnClickListener {
            startActivity(Intent(this, KategoriActivity::class.java))
        }

        // Data Produk
        val buyProducts = listOf(
            BuyProduct("1", "Leopard Gecko", "Beautiful Leopard Gecko", "Rp. 500.000", R.drawable.gecko1),
            BuyProduct("2", "Bearded Dragon", "Amazing Bearded Dragon", "Rp. 750.000", R.drawable.gecko2)
        )

        val auctionProducts = listOf(
            AuctionProduct("1", "Ball Python", "Rare Ball Python", "Rp. 1.000.000", "Rp. 50.000", R.drawable.gecko3),
            AuctionProduct("2", "Corn Snake", "Bright Corn Snake", "Rp. 900.000", "Rp. 40.000", R.drawable.gecko2)
        )

        // RecyclerView Horizontal untuk Produk Lelang
        val rvLelangHewan = findViewById<RecyclerView>(R.id.rvLelangHewanHorizontal)
        rvLelangHewan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvLelangHewan.adapter = AuctionProductAdapter(auctionProducts)

        // RecyclerView Horizontal untuk Produk Jual Beli
        val rvBuyProducts = findViewById<RecyclerView>(R.id.rvBuyProducts)
        rvBuyProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvBuyProducts.adapter = BuyProductAdapter(buyProducts)

        // RecyclerView Horizontal untuk Produk Lelang (duplikat jika diperlukan)
        val rvAuctionProducts = findViewById<RecyclerView>(R.id.rvAuctionProducts)
        rvAuctionProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvAuctionProducts.adapter = AuctionProductAdapter(auctionProducts)



        // Padding untuk Sistem Bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
