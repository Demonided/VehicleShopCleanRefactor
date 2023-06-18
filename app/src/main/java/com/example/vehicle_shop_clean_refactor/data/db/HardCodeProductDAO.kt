package com.example.vehicle_shop_clean_refactor.data.db

import com.example.vehicle_shop_clean_refactor.R
import com.example.vehicle_shop_clean_refactor.domain.dao.ProductDAO
import com.example.vehicle_shop_clean_refactor.Currency
import com.example.vehicle_shop_clean_refactor.Bike
import com.example.vehicle_shop_clean_refactor.Car
import com.example.vehicle_shop_clean_refactor.Product
import com.example.vehicle_shop_clean_refactor.Scooter
import com.example.vehicle_shop_clean_refactor.Price

class HardCodeProductDAO : ProductDAO {
    override fun getProducts(): List<Product> {
        return listOf(
            Car(
                id = 1,
                imageId = R.drawable.old_car,
                name = "Ретро автомобиль",
                mainPrice = Price(1000000, Currency.RUBLE),
                power = 200,
                yearOfManufacture = 1973
            ),
            Car(
                id = 2,
                imageId = R.drawable.sport_car,
                name = "Спортивный автомобиль",
                mainPrice = Price(7000000, Currency.RUBLE),
                power = 500,
                yearOfManufacture = 2020
            ),
            Scooter(
                id = 3,
                imageId = R.drawable.scooter,
                name = "Скутер",
                mainPrice = Price(500000, Currency.RUBLE),
                power = 50
            ),
            Bike(
                id = 4,
                imageId = R.drawable.bike,
                name = "Велосипед",
                mainPrice = Price(25000, Currency.RUBLE),
                countOfSpeeds = 21
            )
        )
    }

    override fun getProductById(id: Int): Product? {
        return getProducts().find { product ->
            product.id == id
        }
    }
}
