package com.example.vehicle_shop_clean_refactor._unsorted

class ProductListPresenter(
    private val view: ProductListView
) {
    private val getProductListUseCase = GetProductListUseCase(HardCodeProductDAO())

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
