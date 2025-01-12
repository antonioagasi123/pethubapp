package id.ac.binus.pethub2.adapter

import id.ac.binus.pethub2.data.AuctionProduct
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.binus.pethub2.activity.GridSpacingItemDecoration
import id.ac.binus.pethub2.R

class LelangPagerAdapter(
    private val context: Context,
    private val products: List<AuctionProduct> // Gunakan tipe data yang sesuai
) : RecyclerView.Adapter<LelangPagerAdapter.LelangPageViewHolder>() {

    inner class LelangPageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rvGrid: RecyclerView = view.findViewById(R.id.rvGrid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LelangPageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout_grid, parent, false)
        return LelangPageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LelangPageViewHolder, position: Int) {
        val pageProducts = products.chunked(6)[position] // Membagi produk menjadi halaman
        val gridLayoutManager = GridLayoutManager(context, 2) // Grid 2 kolom
        holder.rvGrid.layoutManager = gridLayoutManager
        holder.rvGrid.adapter = AuctionProductAdapter(pageProducts) // Mengatur adapter grid

        val spacing = context.resources.getDimensionPixelSize(R.dimen.grid_spacing)
        holder.rvGrid.addItemDecoration(GridSpacingItemDecoration(2, spacing))
    }

    override fun getItemCount(): Int {
        return (products.size + 5) / 6 // Jumlah halaman berdasarkan produk
    }
}
