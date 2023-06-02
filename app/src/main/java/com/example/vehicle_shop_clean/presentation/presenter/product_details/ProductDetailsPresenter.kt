package com.example.vehicle_shop_clean.presentation.presenter.product_details

import android.os.Handler
import android.os.Looper
import com.example.vehicle_shop_clean.creator.Creator
import com.example.vehicle_shop_clean.domain.consumer.Consumer
import com.example.vehicle_shop_clean.domain.consumer.ConsumerData
import com.example.vehicle_shop_clean.domain.model.product_details.ProductDetails
import com.example.vehicle_shop_clean.presentation.mapper.ProductDetailsMapper

class ProductDetailsPresenter(
    private val view: ProductDetailsView
) {
    private val getProductDetailsUseCase = Creator.provideGetProductDetailsUseCase()

    private val handler = Handler(Looper.getMainLooper())

    fun loadData(productId: Int?) {
        view.showLoading()

        getProductDetailsUseCase.execute(
            productId = productId,
            consumer = object : Consumer<ProductDetails> {
                override fun consume(data: ConsumerData<ProductDetails>) {
                    handler.post {
                        when (data) {
                            is ConsumerData.Error -> {
                                view.showError(data.message)
                            }

                            is ConsumerData.Data -> {
                                val productDetailsInfo = ProductDetailsMapper.map(data.value)
                                view.showProductDetails(productDetailsInfo)
                            }
                        }
                    }
                }
            }
        )
    }
}
