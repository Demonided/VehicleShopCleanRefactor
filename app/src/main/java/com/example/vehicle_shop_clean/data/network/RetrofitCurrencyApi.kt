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
        return RetrofitClient.api.getLatestRate(currency.abbr).execute()
            .mapToApiResponse { getLastRateResponse ->
                CurrencyRateMapper.map(currency, getLastRateResponse.rates)
            }
    }
}
