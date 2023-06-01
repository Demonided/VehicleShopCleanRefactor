package com.example.vehicle_shop_clean.presentation.presenter.product_details

import com.example.vehicle_shop_clean.presentation.model.ProductDetailsInfo

interface ProductDetailsView {

    fun showLoading()

    fun showError(message: String)

    fun showProductDetails(productDetailsInfo: ProductDetailsInfo)

}
