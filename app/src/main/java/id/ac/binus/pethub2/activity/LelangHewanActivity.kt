package id.ac.binus.pethub2.activity

import id.ac.binus.pethub2.data.AuctionProduct
import id.ac.binus.pethub2.adapter.AuctionProductAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.binus.pethub2.R

class LelangHewanActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lelang_hewan)

        // Tangani insets untuk sistem bar
        val mainView = findViewById<View>(R.id.main_lelanghewan)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tombol kembali ke HomeActivity
        val ivBack = findViewById<ImageView>(R.id.iv_back)
        ivBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // RecyclerView untuk produk lelang
        val rvLelangHewan = findViewById<RecyclerView>(R.id.rvLelangHewan)

        // Data produk lelang
        val auctionProducts = listOf(
            AuctionProduct(
                id = "1",
                name = "Ball Python",
                description = "Rare Ball Python",
                price = "Rp. 150.000",
                bidIncrement = "Rp. 10.000",
                imageResList = listOf(R.drawable.gecko1)
            ),
            AuctionProduct(
                id = "2",
                name = "Leopard Gecko",
                description = "Beautiful Leopard Gecko",
                price = "Rp. 100.000",
                bidIncrement = "Rp. 10.000",
                imageResList = listOf(R.drawable.gecko2)
            ),
            AuctionProduct(
                id = "3",
                name = "Hognose Snake",
                description = "Amazing Hognose Snake",
                price = "Rp. 170.000",
                bidIncrement = "Rp. 20.000",
                imageResList = listOf(R.drawable.gecko3)
            )
        )

        // Urutkan produk berdasarkan bid increment (konversi ke angka)
        val sortedAuctionProducts = auctionProducts.sortedByDescending {
            it.bidIncrement.replace("Rp.", "").replace(".", "").trim().toInt()
        }


        // Set up RecyclerView
        rvLelangHewan.layoutManager = GridLayoutManager(this, 2)
        rvLelangHewan.adapter = AuctionProductAdapter(sortedAuctionProducts)

        // Tambahkan dekorasi untuk jarak antar item
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.grid_spacing_small)
        rvLelangHewan.addItemDecoration(GridSpacingItemDecoration(2, spacingInPixels))
    }
}

// Class untuk dekorasi jarak antar grid
class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        outRect.left = spacing / 2
        outRect.right = spacing / 2

        if (position < spanCount) {
            outRect.top = spacing / 2
        }
        outRect.bottom = spacing / 2
    }
}
