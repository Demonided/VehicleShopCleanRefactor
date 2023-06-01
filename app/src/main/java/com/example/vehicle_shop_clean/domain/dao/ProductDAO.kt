package com.example.vehicle_shop_clean.domain.dao

import com.example.vehicle_shop_clean.domain.model.product.Product

interface ProductDAO {
    fun getProducts(): List<Product>
    fun getProductById(id: Int): Product?
}
