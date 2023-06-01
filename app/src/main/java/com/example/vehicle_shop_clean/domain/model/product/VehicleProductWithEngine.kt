package com.example.vehicle_shop_clean.domain.model.product

abstract class VehicleProductWithEngine : VehicleWithEngine, Product {

    fun getDescriptionVehicleWithEngine(): String {
        return "Мощность: $power л.с. \nКатегория прав: $driverLicense"
    }
}
