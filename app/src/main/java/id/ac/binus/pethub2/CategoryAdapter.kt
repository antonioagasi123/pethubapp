package id.ac.binus.pethub2

import Subcategory
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
    private val categories: List<Category>,
    private val onCategoryClick: (Category) -> Unit // Listener untuk klik kategori utama
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
        val rvSubcategories: RecyclerView = view.findViewById(R.id.rvSubcategories)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.tvCategoryName.text = category.name

        holder.rvSubcategories.layoutManager =
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.VERTICAL, false)

        holder.rvSubcategories.adapter =
            SubcategoryAdapter(category.subcategories) { subcategory: Subcategory ->
                val context = holder.itemView.context
                val intent = Intent(context, SubcategoryActivity::class.java)
                intent.putExtra("SUBCATEGORY_NAME", subcategory.name)
                intent.putExtra("SUBCATEGORY", subcategory)
                context.startActivity(intent)
            }

        holder.tvCategoryName.setOnClickListener {
            onCategoryClick(category)
        }
    }

    override fun getItemCount(): Int = categories.size
}