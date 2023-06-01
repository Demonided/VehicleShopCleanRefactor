package com.example.vehicle_shop_clean.data.db

import com.example.vehicle_shop_clean.R
import com.example.vehicle_shop_clean.domain.dao.ProductDAO
import com.example.vehicle_shop_clean.domain.model.currency.Currency
import com.example.vehicle_shop_clean.domain.model.product.Bike
import com.example.vehicle_shop_clean.domain.model.product.Car
import com.example.vehicle_shop_clean.domain.model.product.Product
import com.example.vehicle_shop_clean.domain.model.product.Scooter
import com.example.vehicle_shop_clean.domain.model.product_details.Price

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
