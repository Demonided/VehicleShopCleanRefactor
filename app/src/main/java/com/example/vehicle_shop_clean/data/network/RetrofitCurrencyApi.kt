package com.example.vehicle_shop_clean.data.network

import com.example.vehicle_shop_clean.data.dto.GetLatestRateResponse
import com.example.vehicle_shop_clean.domain.api.CurrencyApi
import com.example.vehicle_shop_clean.domain.model.currency.Currency
import com.example.vehicle_shop_clean.domain.model.currency.Rate
import com.example.vehicle_shop_clean.data.mapper.CurrencyRateMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCurrencyApi : CurrencyApi {

    override fun getCurrencyRate(
        currency: Currency,
        onSuccess: (rates: List<Rate>) -> Unit,
        onError: (message: String) -> Unit
    ) {
        RetrofitClient.api.getLatestRate(currency.abbr)
            .enqueue(object : Callback<GetLatestRateResponse> {
                override fun onResponse(
                    call: Call<GetLatestRateResponse>,
                    response: Response<GetLatestRateResponse>
                ) {
                    response.body()?.rates?.let { ratesDto ->
                        onSuccess(CurrencyRateMapper.map(currency, ratesDto))
                    } ?: onError("Не удалось загрузить курс валют")
                }

                override fun onFailure(call: Call<GetLatestRateResponse>, t: Throwable) {
                    onError("Не удалось загрузить курс валют: ${t.message}")
                }

            })
    }
}
