package id.ac.binus.pethub2.data

data class BuyProduct(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val imageResList: List<Int>
)
