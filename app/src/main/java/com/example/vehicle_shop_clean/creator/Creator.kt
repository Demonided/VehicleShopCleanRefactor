package com.example.vehicle_shop_clean.creator

import com.example.vehicle_shop_clean.data.db.HardCodeProductDAO
import com.example.vehicle_shop_clean.data.network.RetrofitCurrencyApi
import com.example.vehicle_shop_clean.domain.api.CurrencyApi
import com.example.vehicle_shop_clean.domain.dao.ProductDAO
import com.example.vehicle_shop_clean.domain.use_case.GetProductDetailsUseCase
import com.example.vehicle_shop_clean.domain.use_case.GetProductListUseCase

object Creator {

    fun provideGetProductListUseCase(): GetProductListUseCase {
        return GetProductListUseCase(provideProductDAO())
    }

    fun provideGetProductDetailsUseCase(): GetProductDetailsUseCase {
        return GetProductDetailsUseCase(provideCurrencyApi(), provideProductDAO())
    }

    private fun provideProductDAO(): ProductDAO {
        return HardCodeProductDAO()
    }

    private fun provideCurrencyApi(): CurrencyApi {
        return RetrofitCurrencyApi()
    }

}
