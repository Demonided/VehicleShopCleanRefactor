package com.example.vehicle_shop_clean.domain.api

import com.example.vehicle_shop_clean.domain.model.currency.Currency
import com.example.vehicle_shop_clean.domain.model.currency.Rate

interface CurrencyApi {

    fun getCurrencyRate(currency: Currency): ApiResponse<List<Rate>>
}
