package com.example.vehicle_shop_clean_refactor.domain.model.product_details

import com.example.vehicle_shop_clean_refactor.domain.model.product.Product

data class ProductDetails(
    val product: Product,
    val costs: List<Price>
)
