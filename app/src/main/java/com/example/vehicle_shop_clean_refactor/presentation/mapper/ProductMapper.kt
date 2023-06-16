package com.example.vehicle_shop_clean_refactor.presentation.mapper

import com.example.vehicle_shop_clean_refactor.domain.model.product.Product
import com.example.vehicle_shop_clean_refactor.presentation.model.ProductInfo

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
