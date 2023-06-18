package com.example.vehicle_shop_clean_refactor

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {

    @GET("latest/{currency}")
    fun getLatestRate(@Path("currency") currency: String): Call<GetLatestRateResponse>
}
