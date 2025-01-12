package id.ac.binus.pethub2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class SlideAdapter(private val images: List<Int>) : RecyclerView.Adapter<SlideAdapter.SlideViewHolder>(){
    inner class SlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewSlider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item, parent, false)
        return SlideViewHolder(view)
    }



    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        holder.imageView.setImageResource(images[position])
        holder.itemView.post {
            Log.d("SliderAdapter", "Item size: ${holder.itemView.width}x${holder.itemView.height}")
        }
    }
}