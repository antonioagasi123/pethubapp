package id.ac.binus.pethub2.data

data class AuctionProduct(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val bidIncrement: String,
    val imageResList: List<Int>
)
