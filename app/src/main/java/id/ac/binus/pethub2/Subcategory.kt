import java.io.Serializable

data class Subcategory(
    val name: String,
    val buyProducts: List<BuyProduct>,
    val auctionProducts: List<AuctionProduct>
) : Serializable

