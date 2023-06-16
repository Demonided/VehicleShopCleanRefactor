package com.example.vehicle_shop_clean_refactor.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val client: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://open.er-api.com/v6/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: RetrofitApi by lazy {
        client.create(RetrofitApi::class.java)
    }
}
