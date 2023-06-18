package com.example.vehicle_shop_clean_refactor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vehicle_shop_clean_refactor.databinding.ActivityProductListBinding
import com.example.vehicle_shop_clean_refactor.ui.product_details.ProductDetailsActivity
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class ProductListActivity : ComponentActivity() {
    private lateinit var adapter: ProductListAdapter
    private lateinit var viewBinding: ActivityProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        adapter = ProductListAdapter(this::showProductDetails)
        val layoutManger = GridLayoutManager(this, 2)

        viewBinding.productList.layoutManager = layoutManger
        viewBinding.productList.adapter = adapter

        showLoading()

        val products = getProducts()
        val productInfos = products.map { product ->
            ProductInfo(
                id = product.id,
                imageId = product.imageId,
                name = product.name,
                priceText = formatPrice(product.mainPrice)
            )
        }
        showProducts(productInfos)
    }

    private fun showLoading() {
        viewBinding.productList.isVisible = false
        viewBinding.loading.isVisible = true
    }

    private fun showProducts(products: List<ProductInfo>) {
        adapter.setItems(products)
        viewBinding.productList.isVisible = true
        viewBinding.loading.isVisible = false
    }

    private fun showProductDetails(productId: Int) {
        ProductDetailsActivity.show(this, productId)
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
}
