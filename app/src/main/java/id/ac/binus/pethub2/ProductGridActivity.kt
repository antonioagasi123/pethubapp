package id.ac.binus.pethub2

import AuctionProduct
import AuctionProductAdapter
import BuyProduct
import BuyProductAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
