import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ac.binus.pethub2.R

class AuctionProductAdapter(
    private val products: List<AuctionProduct>
) : RecyclerView.Adapter<AuctionProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProductName: TextView = view.findViewById(R.id.tvProductName)
        val tvProductPrice: TextView = view.findViewById(R.id.tvProductPrice)
        val tvBidIncrement: TextView = view.findViewById(R.id.tvBidIncrement)
        val ivProductImage: ImageView = view.findViewById(R.id.ivProductImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_auction_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.tvProductName.text = product.name
        holder.tvProductPrice.text = product.price
        holder.tvBidIncrement.text = "Bid Increment: ${product.bidIncrement}"
        Glide.with(holder.itemView.context)
            .load(product.imageResId)
            .into(holder.ivProductImage)
    }

    override fun getItemCount(): Int = products.size
}
