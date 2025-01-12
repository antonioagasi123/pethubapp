package id.ac.binus.pethub2.adapter

import id.ac.binus.pethub2.data.BuyProduct
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import id.ac.binus.pethub2.R

class BuyProductAdapter(private val products: List<BuyProduct>) :
    RecyclerView.Adapter<BuyProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvProductName)
        val tvPrice: TextView = view.findViewById(R.id.tvProductPrice)
        val viewPagerSlider: ViewPager2 = view.findViewById(R.id.viewPagerSlider_buy)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_buy_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.tvName.text = product.name
        holder.tvPrice.text = product.price

        // Set adapter for the slider
        holder.viewPagerSlider.adapter = SlideAdapterBuy(product.imageResList)
    }

    override fun getItemCount(): Int = products.size
}
