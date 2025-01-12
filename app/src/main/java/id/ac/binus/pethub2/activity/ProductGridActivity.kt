package id.ac.binus.pethub2.activity

import id.ac.binus.pethub2.data.AuctionProduct
import id.ac.binus.pethub2.adapter.AuctionProductAdapter
import id.ac.binus.pethub2.data.BuyProduct
import id.ac.binus.pethub2.adapter.BuyProductAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.binus.pethub2.R

class ProductGridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_grid)

        // Inisialisasi RecyclerView
        val rvProducts = findViewById<RecyclerView>(R.id.rvProducts)
        rvProducts.layoutManager = GridLayoutManager(this, 2) // Grid dengan 2 kolom

        // Ambil data dari Intent
        val buyProducts = intent.getSerializableExtra("BUY_PRODUCT_LIST") as? ArrayList<BuyProduct>
        val auctionProducts = intent.getSerializableExtra("AUCTION_PRODUCT_LIST") as? ArrayList<AuctionProduct>

        // Set adapter berdasarkan data yang diterima
        if (buyProducts != null) {
            rvProducts.adapter = BuyProductAdapter(buyProducts)
        } else if (auctionProducts != null) {
            rvProducts.adapter = AuctionProductAdapter(auctionProducts)
        }
    }
}
