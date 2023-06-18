package com.example.vehicle_shop_clean_refactor._unsorted

import com.example.vehicle_shop_clean_refactor._unsorted.ProductDAO
import com.example.vehicle_shop_clean_refactor.Product

class GetProductListUseCase(
    private val productDAO: ProductDAO
) {
    fun execute(): List<Product> {
        return productDAO.getProducts()
    }
}
