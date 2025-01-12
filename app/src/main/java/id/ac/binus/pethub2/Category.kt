package id.ac.binus.pethub2

import Subcategory

data class Category(
    val name: String,
    val iconResId: Int,
    val subcategories: List<Subcategory>
)
