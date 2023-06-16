package com.example.vehicle_shop_clean_refactor.presentation.presenter.product_details

import com.example.vehicle_shop_clean_refactor.presentation.model.ProductDetailsInfo

interface ProductDetailsView {

    fun showLoading()

    fun showError(message: String)

    fun showProductDetails(productDetailsInfo: ProductDetailsInfo)

}
