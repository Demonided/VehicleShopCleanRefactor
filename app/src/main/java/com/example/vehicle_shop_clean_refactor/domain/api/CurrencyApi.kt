package com.example.vehicle_shop_clean_refactor.domain.api

import com.example.vehicle_shop_clean_refactor.Currency
import com.example.vehicle_shop_clean_refactor.Rate

interface CurrencyApi {

    fun getCurrencyRate(currency: Currency): ApiResponse<List<Rate>>
}
