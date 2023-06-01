package com.example.vehicle_shop_clean.domain.model.product_details

import com.example.vehicle_shop_clean.domain.model.product.Product

data class ProductDetails(
    val product: Product,
    val costs: List<Price>
)
