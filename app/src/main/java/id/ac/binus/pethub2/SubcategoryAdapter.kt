package id.ac.binus.pethub2

import Subcategory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubcategoryAdapter(
    private val subcategories: List<Subcategory>,
    private val onSubcategoryClick: (Subcategory) -> Unit
) : RecyclerView.Adapter<SubcategoryAdapter.SubcategoryViewHolder>() {

    inner class SubcategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSubcategoryName: TextView = view.findViewById(R.id.tvSubcategoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_subcategory, parent, false)
        return SubcategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        val subcategory = subcategories[position]
        holder.tvSubcategoryName.text = subcategory.name

        holder.itemView.setOnClickListener {
            onSubcategoryClick(subcategory)
        }
    }

    override fun getItemCount(): Int = subcategories.size
}
