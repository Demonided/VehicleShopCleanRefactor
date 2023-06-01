package com.example.vehicle_shop_clean.domain.model.product

import com.example.vehicle_shop_clean.domain.model.product_details.Price

data class Bike(
    override val id: Int,
    override val name: String,
    override val imageId: Int,
    override val mainPrice: Price,
    private val countOfSpeeds: Int
) : Product {

    override fun getDescription(): String {
        return "Количество скоростей: $countOfSpeeds"
    }
}
