package com.example.vehicle_shop_clean_refactor.data.network

import com.example.vehicle_shop_clean_refactor.domain.api.ApiResponse
import com.example.vehicle_shop_clean_refactor.domain.api.CurrencyApi
import com.example.vehicle_shop_clean_refactor.Currency
import com.example.vehicle_shop_clean_refactor.Rate
import com.example.vehicle_shop_clean_refactor.data.mapper.CurrencyRateMapper

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
