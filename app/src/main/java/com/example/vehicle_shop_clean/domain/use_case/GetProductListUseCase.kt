package com.example.vehicle_shop_clean.domain.use_case

import com.example.vehicle_shop_clean.domain.dao.ProductDAO
import com.example.vehicle_shop_clean.domain.model.product.Product

class GetProductListUseCase(
    private val productDAO: ProductDAO
) {
    fun execute(): List<Product> {
        return productDAO.getProducts()
    }
}
