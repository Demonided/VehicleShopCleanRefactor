package com.example.vehicle_shop_clean_refactor

interface Product {
    val id: Int
    val imageId: Int
    val name: String
    val mainPrice: Price

    fun getDescription(): String
}
