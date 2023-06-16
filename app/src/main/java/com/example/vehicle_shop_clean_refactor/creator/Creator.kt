package com.example.vehicle_shop_clean_refactor.creator

import com.example.vehicle_shop_clean_refactor.data.db.HardCodeProductDAO
import com.example.vehicle_shop_clean_refactor.data.network.RetrofitCurrencyApi
import com.example.vehicle_shop_clean_refactor.domain.api.CurrencyApi
import com.example.vehicle_shop_clean_refactor.domain.dao.ProductDAO
import com.example.vehicle_shop_clean_refactor.domain.use_case.GetProductDetailsUseCase
import com.example.vehicle_shop_clean_refactor.domain.use_case.GetProductListUseCase

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
