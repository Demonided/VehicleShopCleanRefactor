package com.example.vehicle_shop_clean_refactor._unsorted

import com.example.vehicle_shop_clean_refactor.Price
import com.example.vehicle_shop_clean_refactor.ProductDetails
import com.example.vehicle_shop_clean_refactor.ProductDetailsInfo

object ProductDetailsMapper {

    fun map(productDetails: ProductDetails): ProductDetailsInfo {
        return ProductDetailsInfo(
            id = productDetails.product.id,
            imageId = productDetails.product.imageId,
            name = productDetails.product.name,
            description = productDetails.product.getDescription(),
            pricesText = formatPrices(productDetails.costs)
        )
    }

    private fun formatPrices(prices: List<Price>): String {
        return "Стоимость: \n" + prices.joinToString(separator = "\n") { price ->
            PriceFormatter.formatPrice(price)
        }
    }
}
