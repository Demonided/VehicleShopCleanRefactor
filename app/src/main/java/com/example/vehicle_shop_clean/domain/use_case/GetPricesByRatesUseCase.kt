package com.example.vehicle_shop_clean.domain.use_case

import com.example.vehicle_shop_clean.domain.model.currency.Rate
import com.example.vehicle_shop_clean.domain.model.product_details.Price

object GetPricesByRatesUseCase {

    fun execute(mainPrice: Price, rates: List<Rate>): List<Price> {
        return rates.map { rate ->
            getPriceByRate(mainPrice, rate)
        }.filterNotNull()
    }

    private fun getPriceByRate(mainPrice: Price, rate: Rate): Price? {
        if (mainPrice.currency == rate.mainCurrency) {
            return Price(
                value = mainPrice.value * rate.value,
                currency = rate.currency
            )
        }

        return null
    }
}
