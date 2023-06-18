package com.example.vehicle_shop_clean_refactor._unsorted

import com.example.vehicle_shop_clean_refactor.ProductDetailsInfo

interface ProductDetailsView {

    fun showLoading()

    fun showError(message: String)

    fun showProductDetails(productDetailsInfo: ProductDetailsInfo)

}
