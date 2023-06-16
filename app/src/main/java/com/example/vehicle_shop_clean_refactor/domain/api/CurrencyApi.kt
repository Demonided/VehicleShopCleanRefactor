package com.example.vehicle_shop_clean_refactor.domain.api

import com.example.vehicle_shop_clean_refactor.domain.model.currency.Currency
import com.example.vehicle_shop_clean_refactor.domain.model.currency.Rate

interface CurrencyApi {

    fun getCurrencyRate(currency: Currency): ApiResponse<List<Rate>>
}
