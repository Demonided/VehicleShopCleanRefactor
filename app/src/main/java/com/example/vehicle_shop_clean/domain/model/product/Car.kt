package com.example.vehicle_shop_clean.domain.model.product

import com.example.vehicle_shop_clean.domain.model.product_details.Price

data class Car(
    override val id: Int,
    override val imageId: Int,
    override val name: String,
    override val mainPrice: Price,
    override val power: Int,
    private val yearOfManufacture: Int
) : VehicleProductWithEngine() {
    override val driverLicense: String
        get() = "B"

    override fun getDescription(): String {
        return getDescriptionVehicleWithEngine() + "\nГод выпуска: $yearOfManufacture"
    }
}
