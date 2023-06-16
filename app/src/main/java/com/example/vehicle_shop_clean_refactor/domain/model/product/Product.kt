package com.example.vehicle_shop_clean_refactor.domain.model.product

import com.example.vehicle_shop_clean_refactor.domain.model.product_details.Price

interface Product {
    val id: Int
    val imageId: Int
    val name: String
    val mainPrice: Price

    fun getDescription(): String
}
