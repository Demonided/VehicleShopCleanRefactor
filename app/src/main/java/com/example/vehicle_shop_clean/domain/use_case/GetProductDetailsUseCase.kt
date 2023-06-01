package com.example.vehicle_shop_clean.domain.use_case

import com.example.vehicle_shop_clean.domain.api.CurrencyApi
import com.example.vehicle_shop_clean.domain.dao.ProductDAO
import com.example.vehicle_shop_clean.domain.model.product_details.ProductDetails
import com.example.vehicle_shop_clean.domain.model.currency.Rate
import com.example.vehicle_shop_clean.domain.model.product.Product

class GetProductDetailsUseCase(
    private val currencyApi: CurrencyApi,
    private val productDAO: ProductDAO
) {
    fun execute(
        productId: Int?,
        onProductDetailsLoaded: (details: ProductDetails) -> Unit,
        onProductDetailsNotLoaded: (error: String) -> Unit
    ) {
        val product = productId?.let { productDAO.getProductById(productId) }

        if (product == null) {
            onProductDetailsNotLoaded("Что-то пошло не так, попробуйте еще раз :(")
        } else {
            currencyApi.getCurrencyRate(
                currency = product.mainPrice.currency,
                onSuccess = { rates ->
                    val productDetails = getProductDetails(product, rates)
                    onProductDetailsLoaded(productDetails)
                },
                onError = { message ->
                    println(message)

                    onProductDetailsLoaded(ProductDetails(product, listOf(product.mainPrice)))
                }
            )
        }
    }

    private fun getProductDetails(product: Product, rates: List<Rate>): ProductDetails {
        val prices = GetPricesByRatesUseCase.execute(product.mainPrice, rates)
        return ProductDetails(product, prices)
    }
}
