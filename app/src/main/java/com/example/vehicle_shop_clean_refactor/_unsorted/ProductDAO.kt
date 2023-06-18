package com.example.vehicle_shop_clean_refactor._unsorted

import com.example.vehicle_shop_clean_refactor.Product

interface ProductDAO {
    fun getProducts(): List<Product>
    fun getProductById(id: Int): Product?
}
