package id.ac.binus.pethub2

import AuctionProduct
import BuyProduct
import id.ac.binus.pethub2.R

object DataSource {
    val buyProducts = listOf(
        BuyProduct(
            id = "1",
            name = "Leopard Gecko",
            description = "Beautiful Leopard Gecko",
            price = "Rp. 500.000",
            imageResList = listOf(R.drawable.gecko1, R.drawable.gecko2)
        ),
        BuyProduct(
            id = "2",
            name = "Bearded Dragon",
            description = "Amazing Bearded Dragon",
            price = "Rp. 750.000",
            imageResList = listOf(R.drawable.gecko3, R.drawable.gecko2)
        )
    )


    val auctionProducts = listOf(
        AuctionProduct(
            id = "1",
            name = "Ball Python",
            description = "Rare Ball Python",
            price = "Rp. 1.000.000",
            bidIncrement = "Rp. 50.000",
            imageResList = listOf(R.drawable.gecko1, R.drawable.gecko2)
        ),
        AuctionProduct(
            id = "2",
            name = "Corn Snake",
            description = "Bright Corn Snake",
            price = "Rp. 900.000",
            bidIncrement = "Rp. 40.000",
            imageResList = listOf(R.drawable.gecko3, R.drawable.gecko2)
        )
    )
}
