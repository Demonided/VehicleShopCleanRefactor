package com.example.vehicle_shop_clean.data.network

import com.example.vehicle_shop_clean.domain.api.ApiResponse
import com.example.vehicle_shop_clean.domain.api.CurrencyApi
import com.example.vehicle_shop_clean.domain.model.currency.Currency
import com.example.vehicle_shop_clean.domain.model.currency.Rate
import com.example.vehicle_shop_clean.data.mapper.CurrencyRateMapper

class RetrofitCurrencyApi : CurrencyApi {

    override fun getCurrencyRate(
        currency: Currency
    ): ApiResponse<List<Rate>> {
        val networkResponse = RetrofitClient.api.getLatestRate(currency.abbr).execute()
        val ratesDto = networkResponse.body()?.rates

        return when {
            !networkResponse.isSuccessful -> {
                ApiResponse.Error(networkResponse.errorBody()?.string() ?: DEFAULT_ERROR_MESSAGE)
            }

            ratesDto != null -> {
                ApiResponse.Success(CurrencyRateMapper.map(currency, ratesDto))
            }

            else -> {
                ApiResponse.Error(DEFAULT_ERROR_MESSAGE)
            }
        }
    }

    companion object {
        private const val DEFAULT_ERROR_MESSAGE = "Не удалось загрузить курс валют"
    }
}
