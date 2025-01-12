package id.ac.binus.pethub2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ac.binus.pethub2.R

class SlideAdapterBuy(private val images: List<Int>) : RecyclerView.Adapter<SlideAdapterBuy.SlideViewHolder>() {

    inner class SlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewSliderBuy)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slide_item_buy, parent, false)
        return SlideViewHolder(view)
    }

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        val imageResId = images[position]
        // Load the image into the ImageView
        Glide.with(holder.itemView.context).load(imageResId).into(holder.imageView)
    }

    override fun getItemCount(): Int = images.size
}
