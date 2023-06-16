package com.example.vehicle_shop_clean_refactor.domain.model.product

abstract class VehicleProductWithEngine : VehicleWithEngine, Product {

    fun getDescriptionVehicleWithEngine(): String {
        return "Мощность: $power л.с. \nКатегория прав: $driverLicense"
    }
}
