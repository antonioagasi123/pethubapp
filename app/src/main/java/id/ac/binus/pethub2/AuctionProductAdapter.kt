import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import id.ac.binus.pethub2.R
import id.ac.binus.pethub2.SlideAdapter
import me.relex.circleindicator.CircleIndicator3

class AuctionProductAdapter(
    private val products: List<AuctionProduct>
) : RecyclerView.Adapter<AuctionProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProductName: TextView = view.findViewById(R.id.tvProductName)
        val tvProductPrice: TextView = view.findViewById(R.id.tvProductPrice)
        val tvBidIncrement: TextView = view.findViewById(R.id.tvBidIncrement)
        val viewPagerSlider: ViewPager2 = view.findViewById(R.id.viewPagerSlider_auction)
        val indicator: CircleIndicator3 = view.findViewById(R.id.indicator_auction)
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

        // Set slider images (assuming product has a list of image resources)
        val sliderImages = product.imageResList// Ensure this field is a list of image resource IDs
        holder.viewPagerSlider.adapter = SlideAdapterAuction(sliderImages)
        holder.indicator.setViewPager(holder.viewPagerSlider)
    }

    override fun getItemCount(): Int = products.size
}
