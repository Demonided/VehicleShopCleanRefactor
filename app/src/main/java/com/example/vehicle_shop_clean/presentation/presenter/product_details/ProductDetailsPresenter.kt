package com.example.vehicle_shop_clean.presentation.presenter.product_details

import com.example.vehicle_shop_clean.creator.Creator
import com.example.vehicle_shop_clean.presentation.mapper.ProductDetailsMapper

class ProductDetailsPresenter(
    private val view: ProductDetailsView
) {
    private val getProductDetailsUseCase = Creator.provideGetProductDetailsUseCase()

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
