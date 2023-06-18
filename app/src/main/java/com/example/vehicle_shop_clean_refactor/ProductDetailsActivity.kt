package com.example.vehicle_shop_clean_refactor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import com.example.vehicle_shop_clean_refactor.databinding.ActivityProductDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class ProductDetailsActivity : ComponentActivity() {
    private lateinit var viewBinding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        showLoading()

        val productId = intent.getIntExtra(PRODUCT_ID, -1)
        val product = getProductById(productId)
        if (product == null) {
            showError("Что-то пошло не так, попробуйте еще раз")
        } else {
            RetrofitClient.api.getLatestRate(product.mainPrice.currency.abbr)
                .enqueue(object : Callback<GetLatestRateResponse> {
                    override fun onResponse(
                        call: Call<GetLatestRateResponse>,
                        response: Response<GetLatestRateResponse>
                    ) {
                        val rateDto = response.body()?.rates
                        if (rateDto == null) {
                            val productDetailsInfo = ProductDetailsInfo(
                                id = product.id,
                                imageId = product.imageId,
                                name = product.name,
                                description = product.getDescription(),
                                pricesText = formatPrice(product.mainPrice)
                            )

                            showProductDetails(productDetailsInfo)
                        } else {
                            val rates = rateDto.mapNotNull { (abbr, rateValue) ->
                                val currency = Currency.values().find { it.abbr == abbr }

                                if (currency != null) {
                                    Rate(product.mainPrice.currency, currency, rateValue)
                                } else {
                                    null
                                }
                            }

                            val prices = rates.mapNotNull { rate ->
                                getPriceByRate(product.mainPrice, rate)
                            }

                            val productDetailsInfo = ProductDetailsInfo(
                                id = product.id,
                                imageId = product.imageId,
                                name = product.name,
                                description = product.getDescription(),
                                pricesText = formatPriceList(prices)
                            )

                            showProductDetails(productDetailsInfo)
                        }
                    }

                    override fun onFailure(call: Call<GetLatestRateResponse>, t: Throwable) {
                        t.printStackTrace()

                        val productDetailsInfo = ProductDetailsInfo(
                            id = product.id,
                            imageId = product.imageId,
                            name = product.name,
                            description = product.getDescription(),
                            pricesText = formatPrice(product.mainPrice)
                        )

                        showProductDetails(productDetailsInfo)
                    }
                })
        }
    }

    private fun showLoading() {
        findViewById<FrameLayout>(R.id.loading)?.let { progressBar ->
            progressBar.isVisible = true
        }
    }

    private fun showProductDetails(productDetailsInfo: ProductDetailsInfo) {
        viewBinding.productImage.setImageResource(productDetailsInfo.imageId)
        viewBinding.productName.text = productDetailsInfo.name
        viewBinding.productDescription.text = productDetailsInfo.description
        viewBinding.productCost.text = productDetailsInfo.pricesText

        viewBinding.loading.isVisible = false
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        onBackPressedDispatcher.onBackPressed()
    }

    private fun getProductById(id: Int): Product? {
        return getProducts().find { product ->
            product.id == id
        }
    }

    private fun getProducts(): List<Product> {
        return listOf(
            Car(
                id = 1,
                imageId = R.drawable.old_car,
                name = "Ретро автомобиль",
                mainPrice = Price(1000000, Currency.RUBLE),
                power = 200,
                yearOfManufacture = 1973
            ),
            Car(
                id = 2,
                imageId = R.drawable.sport_car,
                name = "Спортивный автомобиль",
                mainPrice = Price(7000000, Currency.RUBLE),
                power = 500,
                yearOfManufacture = 2020
            ),
            Scooter(
                id = 3,
                imageId = R.drawable.scooter,
                name = "Скутер",
                mainPrice = Price(500000, Currency.RUBLE),
                power = 50
            ),
            Bike(
                id = 4,
                imageId = R.drawable.bike,
                name = "Велосипед",
                mainPrice = Price(25000, Currency.RUBLE),
                countOfSpeeds = 21
            )
        )
    }

    private fun formatPrice(price: Price): String {
        val decimalFormat = DecimalFormat()
        decimalFormat.isDecimalSeparatorAlwaysShown = false
        decimalFormat.groupingSize = 3
        decimalFormat.maximumFractionDigits = 2

        val decimalFormatSymbols = DecimalFormatSymbols()
        decimalFormatSymbols.groupingSeparator = ' '

        decimalFormat.decimalFormatSymbols = decimalFormatSymbols

        val priceValue = decimalFormat.format(price.value)

        return "$priceValue ${price.currency.symbol}"
    }

    private fun formatPriceList(prices: List<Price>): String {
        return "Стоимость: \n" + prices.joinToString(separator = "\n") { price ->
            formatPrice(price)
        }
    }

    private fun getPriceByRate(mainPrice: Price, rate: Rate): Price? {
        if (mainPrice.currency == rate.mainCurrency) {
            return Price(
                value = mainPrice.value * rate.value,
                currency = rate.currency
            )
        }

        return null
    }

    companion object {
        private const val PRODUCT_ID = "product_id"

        fun show(context: Context, productId: Int) {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra(PRODUCT_ID, productId)

            context.startActivity(intent)
        }
    }
}