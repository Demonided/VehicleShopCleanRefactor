package com.example.vehicle_shop_clean_refactor.domain.use_case

import com.example.vehicle_shop_clean_refactor.domain.api.ApiResponse
import com.example.vehicle_shop_clean_refactor.domain.api.CurrencyApi
import com.example.vehicle_shop_clean_refactor.domain.consumer.Consumer
import com.example.vehicle_shop_clean_refactor.domain.consumer.ConsumerData
import com.example.vehicle_shop_clean_refactor.domain.dao.ProductDAO
import com.example.vehicle_shop_clean_refactor.ProductDetails
import java.util.concurrent.Executors

class GetProductDetailsUseCase(
    private val currencyApi: CurrencyApi,
    private val productDAO: ProductDAO
) {
    private val executor = Executors.newCachedThreadPool()
    fun execute(
        productId: Int?,
        consumer: Consumer<ProductDetails>
    ) {
        executor.execute {
            val product = productId?.let { productDAO.getProductById(productId) }

            if (product == null) {
                consumer.consume(ConsumerData.Error("Что-то пошло не так, попробуйте еще раз :("))
            } else {
                when (val currencyResponse =
                    currencyApi.getCurrencyRate(product.mainPrice.currency)) {
                    is ApiResponse.Success -> {
                        val prices = GetPricesByRatesUseCase.execute(
                            mainPrice = product.mainPrice,
                            rates = currencyResponse.data
                        )
                        val productDetails = ProductDetails(product, prices)

                        consumer.consume(ConsumerData.Data(productDetails))
                    }

                    is ApiResponse.Error -> {
                        println(currencyResponse.message)
                        val productDetails = ProductDetails(product, listOf(product.mainPrice))

                        consumer.consume(ConsumerData.Data(productDetails))
                    }
                }
            }
        }
    }
}
