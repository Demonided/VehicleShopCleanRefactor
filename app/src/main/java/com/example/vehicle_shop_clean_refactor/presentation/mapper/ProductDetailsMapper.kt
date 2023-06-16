package com.example.vehicle_shop_clean_refactor.presentation.mapper

import com.example.vehicle_shop_clean_refactor.domain.model.product_details.Price
import com.example.vehicle_shop_clean_refactor.domain.model.product_details.ProductDetails
import com.example.vehicle_shop_clean_refactor.presentation.model.ProductDetailsInfo

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
