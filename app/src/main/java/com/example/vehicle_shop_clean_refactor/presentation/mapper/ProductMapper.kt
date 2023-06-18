package com.example.vehicle_shop_clean_refactor.presentation.mapper

import com.example.vehicle_shop_clean_refactor.Product
import com.example.vehicle_shop_clean_refactor.ProductInfo

object ProductMapper {

    fun map(product: Product): ProductInfo {
        return ProductInfo(
            id = product.id,
            imageId = product.imageId,
            name = product.name,
            priceText = PriceFormatter.formatPrice(product.mainPrice)
        )
    }
}
