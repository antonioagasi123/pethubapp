package id.ac.binus.pethub2.data

data class Category(
    val name: String,
    val iconResId: Int,
    val subcategories: List<Subcategory>
)
