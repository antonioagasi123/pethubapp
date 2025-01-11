package id.ac.binus.pethub2

import Subcategory

data class Category(
    val name: String,
    val subcategories: List<Subcategory>
)