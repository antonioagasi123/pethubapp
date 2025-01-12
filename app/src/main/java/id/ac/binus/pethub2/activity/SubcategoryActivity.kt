package id.ac.binus.pethub2.activity

import id.ac.binus.pethub2.adapter.AuctionProductAdapter
import id.ac.binus.pethub2.adapter.BuyProductAdapter
import id.ac.binus.pethub2.data.Subcategory
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.binus.pethub2.R

class SubcategoryActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subcategory)

        // Ambil data dari Intent
        val subcategoryName = intent.getStringExtra("SUBCATEGORY_NAME") ?: "Unknown id.ac.binus.pethub2.data.Subcategory"
        val subcategory = intent.getSerializableExtra("SUBCATEGORY") as? Subcategory

        // Validasi null pada subkategori
        if (subcategory == null) {
            Toast.makeText(this, "Subkategori tidak ditemukan.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Atur judul subkategori
        findViewById<TextView>(R.id.tvSubcategoryTitle).text = subcategoryName

        // RecyclerView untuk Jual Beli Hewan
        val rvBuyProducts = findViewById<RecyclerView>(R.id.rvBuyProducts)
        rvBuyProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        if (subcategory.buyProducts.isNotEmpty()) {
            rvBuyProducts.adapter = BuyProductAdapter(subcategory.buyProducts)
        } else {
            Toast.makeText(this, "Tidak ada produk jual beli tersedia.", Toast.LENGTH_SHORT).show()
        }

        // RecyclerView untuk Lelang Hewan
        val rvAuctionProducts = findViewById<RecyclerView>(R.id.rvAuctionProducts)
        rvAuctionProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        if (subcategory.auctionProducts.isNotEmpty()) {
            rvAuctionProducts.adapter = AuctionProductAdapter(subcategory.auctionProducts)
        } else {
            Toast.makeText(this, "Tidak ada produk lelang tersedia.", Toast.LENGTH_SHORT).show()
        }
    }
}
