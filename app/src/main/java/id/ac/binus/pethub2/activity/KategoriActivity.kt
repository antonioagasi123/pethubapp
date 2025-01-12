package id.ac.binus.pethub2.activity

import id.ac.binus.pethub2.data.AuctionProduct
import id.ac.binus.pethub2.data.BuyProduct
import id.ac.binus.pethub2.data.Subcategory
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.binus.pethub2.data.Category
import id.ac.binus.pethub2.adapter.CategoryAdapter
import id.ac.binus.pethub2.R

class KategoriActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        val rvKategoriHewan = findViewById<RecyclerView>(R.id.rvKategoriHewan)

        val categories = listOf(
            Category(
                name = "Kadal",
                iconResId = R.drawable.kadal, // Ganti dengan ikon yang sesuai
                subcategories = listOf(
                    Subcategory(
                        name = "Leopard Gecko",
                        buyProducts = listOf(),
                        auctionProducts = listOf()
                    )
                )
            ),
            Category(
                name = "Ular",
                iconResId = R.drawable.ballpython,
                subcategories = listOf()
            )
        )

        val buyProducts = listOf(
            BuyProduct(
                id = "1",
                name = "Leopard Gecko",
                description = "Beautiful Leopard Gecko",
                price = "Rp. 500.000",
                imageResList = listOf(R.drawable.gecko1)
            ),
            BuyProduct(
                id = "2",
                name = "Bearded Dragon",
                description = "Amazing Bearded Dragon",
                price = "Rp. 750.000",
                imageResList = listOf(R.drawable.gecko2)
            )
        )

        val auctionProducts = listOf(
            AuctionProduct(
                id = "1",
                name = "Ball Python",
                description = "Rare Ball Python",
                price = "Rp. 1.000.000",
                bidIncrement = "Rp. 50.000",
                imageResList = listOf(R.drawable.gecko3) // Gunakan list
            ),
            AuctionProduct(
                id = "2",
                name = "Corn Snake",
                description = "Bright Corn Snake",
                price = "Rp. 900.000",
                bidIncrement = "Rp. 40.000",
                imageResList = listOf(R.drawable.gecko2) // Gunakan list
            )
        )

        rvKategoriHewan.layoutManager = GridLayoutManager(this,3)
        rvKategoriHewan.adapter = CategoryAdapter(categories) { category ->
            val filteredBuyProducts = buyProducts.filter { it.name.contains(category.name, ignoreCase = true) }
            val filteredAuctionProducts = auctionProducts.filter { it.name.contains(category.name, ignoreCase = true) }

            if (category.subcategories.isEmpty()) {
                Toast.makeText(this, "Tidak ada subkategori", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SubcategoryActivity::class.java)
                intent.putExtra("CATEGORY_NAME", category.name)
                intent.putExtra("BUY_PRODUCT_LIST", ArrayList(filteredBuyProducts)) // Kirim data jual beli yang difilter
                intent.putExtra("AUCTION_PRODUCT_LIST", ArrayList(filteredAuctionProducts)) // Kirim data lelang yang difilter
                startActivity(intent)
            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_kategori)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
