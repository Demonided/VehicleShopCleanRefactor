package com.example.vehicle_shop_clean.presentation.presenter.product_list

import com.example.vehicle_shop_clean.creator.Creator
import com.example.vehicle_shop_clean.presentation.mapper.ProductMapper

class ProductListPresenter(
    private val view: ProductListView
) {
    private val getProductListUseCase = Creator.provideGetProductListUseCase()

    fun loadData() {
        view.showLoading()

        val products = getProductListUseCase.execute()
        val productInfos = products.map { product ->
            ProductMapper.map(product)
        }

        view.showProducts(productInfos)
    }

    fun clickProduct(productId: Int) {
        view.showProductDetails(productId)
    }
}
