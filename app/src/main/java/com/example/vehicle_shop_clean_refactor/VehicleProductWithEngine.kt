package com.example.vehicle_shop_clean_refactor

abstract class VehicleProductWithEngine : VehicleWithEngine, Product {

    fun getDescriptionVehicleWithEngine(): String {
        return "Мощность: $power л.с. \nКатегория прав: $driverLicense"
    }
}
