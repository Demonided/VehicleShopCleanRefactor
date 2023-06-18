package com.example.vehicle_shop_clean_refactor._unsorted

import com.example.vehicle_shop_clean_refactor.Currency
import com.example.vehicle_shop_clean_refactor.Rate
import com.example.vehicle_shop_clean_refactor.RetrofitClient

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
