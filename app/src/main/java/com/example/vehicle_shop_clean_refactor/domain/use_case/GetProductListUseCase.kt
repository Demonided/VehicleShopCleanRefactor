package com.example.vehicle_shop_clean_refactor.domain.use_case

import com.example.vehicle_shop_clean_refactor.domain.dao.ProductDAO
import com.example.vehicle_shop_clean_refactor.domain.model.product.Product

class GetProductListUseCase(
    private val productDAO: ProductDAO
) {
    fun execute(): List<Product> {
        return productDAO.getProducts()
    }
}
