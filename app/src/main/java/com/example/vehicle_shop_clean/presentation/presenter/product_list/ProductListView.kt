package com.example.vehicle_shop_clean.presentation.presenter.product_list

import com.example.vehicle_shop_clean.presentation.model.ProductInfo

interface ProductListView {

    fun showLoading()

    fun showProducts(products: List<ProductInfo>)

    fun showProductDetails(productId: Int)

    fun showError(error: String)
}
