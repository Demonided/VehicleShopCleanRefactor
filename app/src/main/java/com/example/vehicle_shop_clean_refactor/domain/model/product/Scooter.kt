package com.example.vehicle_shop_clean_refactor.domain.model.product

import com.example.vehicle_shop_clean_refactor.domain.model.product_details.Price

data class Scooter(
    override val id: Int,
    override val imageId: Int,
    override val name: String,
    override val mainPrice: Price,
    override val power: Int
) : VehicleProductWithEngine() {
    override val driverLicense: String
        get() = "M"

    override fun getDescription(): String {
        return getDescriptionVehicleWithEngine()
    }
}