package com.example.vehicle_shop_clean_refactor.presentation.presenter.product_details

import android.os.Handler
import android.os.Looper
import com.example.vehicle_shop_clean_refactor.creator.Creator
import com.example.vehicle_shop_clean_refactor.domain.consumer.Consumer
import com.example.vehicle_shop_clean_refactor.domain.consumer.ConsumerData
import com.example.vehicle_shop_clean_refactor.domain.model.product_details.ProductDetails
import com.example.vehicle_shop_clean_refactor.presentation.mapper.ProductDetailsMapper

class ProductDetailsPresenter(
    private val view: ProductDetailsView
) {
    private val getProductDetailsUseCase = Creator.provideGetProductDetailsUseCase()

    private val handler = Handler(Looper.getMainLooper())
    private var currentConsumeRunnable: Runnable? = null

    fun loadData(productId: Int?) {
        view.showLoading()

        getProductDetailsUseCase.execute(
            productId = productId,
            consumer = object : Consumer<ProductDetails> {
                override fun consume(data: ConsumerData<ProductDetails>) {
                    handler.removeCallbacksSafe(currentConsumeRunnable)

                    val consumeRunnable = getConsumeRunnable(data)
                    currentConsumeRunnable = consumeRunnable

                    handler.post(consumeRunnable)
                }
            }
        )
    }

    private fun getConsumeRunnable(data: ConsumerData<ProductDetails>): Runnable {
        return Runnable {
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

    fun onDestroy() {
        handler.removeCallbacksSafe(currentConsumeRunnable)
    }

    private fun Handler.removeCallbacksSafe(r: Runnable?) {
        r?.let {
            removeCallbacks(r)
        }
    }
}
