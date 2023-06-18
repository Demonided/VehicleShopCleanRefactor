package com.example.vehicle_shop_clean_refactor._unsorted

import com.example.vehicle_shop_clean_refactor.ProductInfo

interface ProductListView {

    fun showLoading()

    fun showProducts(products: List<ProductInfo>)

    fun showProductDetails(productId: Int)

    fun showError(error: String)
}
