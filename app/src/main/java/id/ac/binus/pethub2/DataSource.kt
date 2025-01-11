package id.ac.binus.pethub2

import AuctionProduct
import BuyProduct

object DataSource {
    val buyProducts = listOf(
        BuyProduct(
            id = "1",
            name = "Leopard Gecko",
            description = "Beautiful Leopard Gecko",
            price = "Rp. 500.000",
            imageResId = R.drawable.gecko1
        ),
        BuyProduct(
            id = "2",
            name = "Bearded Dragon",
            description = "Amazing Bearded Dragon",
            price = "Rp. 750.000",
            imageResId = R.drawable.gecko2
        )
    )

    val auctionProducts = listOf(
        AuctionProduct(
            id = "1",
            name = "Ball Python",
            description = "Rare Ball Python",
            price = "Rp. 1.000.000",
            bidIncrement = "Rp. 50.000",
            imageResId = R.drawable.gecko3
        ),
        AuctionProduct(
            id = "2",
            name = "Corn Snake",
            description = "Bright Corn Snake",
            price = "Rp. 900.000",
            bidIncrement = "Rp. 40.000",
            imageResId = R.drawable.gecko2
        )
    )
}
