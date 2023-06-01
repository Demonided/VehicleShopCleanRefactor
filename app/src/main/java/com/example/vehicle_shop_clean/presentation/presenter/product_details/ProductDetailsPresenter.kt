package com.example.vehicle_shop_clean.presentation.presenter.product_details

import com.example.vehicle_shop_clean.data.db.HardCodeProductDAO
import com.example.vehicle_shop_clean.data.network.RetrofitCurrencyApi
import com.example.vehicle_shop_clean.domain.use_case.GetProductDetailsUseCase
import com.example.vehicle_shop_clean.presentation.mapper.ProductDetailsMapper

class ProductDetailsPresenter(
    private val view: ProductDetailsView
) {
    private val productDAO = HardCodeProductDAO()
    private val currencyApi = RetrofitCurrencyApi()

    private val getProductDetailsUseCase = GetProductDetailsUseCase(currencyApi, productDAO)

    fun loadData(productId: Int?) {
        view.showLoading()

        getProductDetailsUseCase.execute(
            productId = productId,
            onProductDetailsNotLoaded = { error ->
                view.showError(error)
            },
            onProductDetailsLoaded = { productDetails ->
                val productDetailsInfo = ProductDetailsMapper.map(productDetails)
                view.showProductDetails(productDetailsInfo)
            }
        )
    }
}
