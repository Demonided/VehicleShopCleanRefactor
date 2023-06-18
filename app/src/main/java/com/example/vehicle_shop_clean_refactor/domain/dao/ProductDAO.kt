package com.example.vehicle_shop_clean_refactor.domain.dao

import com.example.vehicle_shop_clean_refactor.Product

interface ProductDAO {
    fun getProducts(): List<Product>
    fun getProductById(id: Int): Product?
}
