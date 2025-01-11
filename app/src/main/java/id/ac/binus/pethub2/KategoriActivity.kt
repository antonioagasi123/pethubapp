package id.ac.binus.pethub2

import AuctionProduct
import BuyProduct
import Subcategory
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class KategoriActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        val rvKategori = findViewById<RecyclerView>(R.id.rvKategori)

        val categories = listOf(
            Category(
                name = "Kadal",
                subcategories = listOf(
                    Subcategory(
                        name = "Leopard Gecko",
                        buyProducts = listOf(),
                        auctionProducts = listOf()
                    )
                )
            )
        )

        val buyProducts = listOf(
            BuyProduct(
                id = "1",
                name = "Leopard Gecko",
                description = "Beautiful Leopard Gecko",
                price = "Rp. 500.000",
                imageResId = R.drawable.gecko1
            ),
            BuyProduct(
                id = "2",
                name = "Bearded Dragon",
                description = "Amazing Bearded Dragon",
                price = "Rp. 750.000",
                imageResId = R.drawable.gecko2
            )
        )

        val auctionProducts = listOf(
            AuctionProduct(
                id = "1",
                name = "Ball Python",
                description = "Rare Ball Python",
                price = "Rp. 1.000.000",
                bidIncrement = "Rp. 50.000",
                imageResId = R.drawable.gecko3
            ),
            AuctionProduct(
                id = "2",
                name = "Corn Snake",
                description = "Bright Corn Snake",
                price = "Rp. 900.000",
                bidIncrement = "Rp. 40.000",
                imageResId = R.drawable.gecko2
            )
        )

        rvKategori.layoutManager = LinearLayoutManager(this)
        rvKategori.adapter = CategoryAdapter(categories) { category ->
            val intent = Intent(this, SubcategoryActivity::class.java)
            intent.putExtra("CATEGORY_NAME", category.name)
            intent.putExtra("BUY_PRODUCT_LIST", ArrayList(buyProducts)) // Kirim data jual beli
            intent.putExtra("AUCTION_PRODUCT_LIST", ArrayList(auctionProducts)) // Kirim data lelang
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_kategori)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
