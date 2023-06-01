package com.example.vehicle_shop_clean.presentation.mapper

import com.example.vehicle_shop_clean.domain.model.product.Product
import com.example.vehicle_shop_clean.presentation.model.ProductInfo

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
