package com.example.vehicle_shop_clean.ui.product_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import com.example.vehicle_shop_clean.R
import com.example.vehicle_shop_clean.databinding.ActivityProductDetailsBinding
import com.example.vehicle_shop_clean.presentation.model.ProductDetailsInfo
import com.example.vehicle_shop_clean.presentation.presenter.product_details.ProductDetailsPresenter
import com.example.vehicle_shop_clean.presentation.presenter.product_details.ProductDetailsView

class ProductDetailsActivity : ComponentActivity(), ProductDetailsView {

    private val presenter = ProductDetailsPresenter(this)
    private lateinit var viewBinding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        presenter.loadData(intent.getIntExtra(PRODUCT_ID, -1))
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showLoading() {
        findViewById<FrameLayout>(R.id.loading)?.let { progressBar ->
            progressBar.isVisible = true
        }
    }

    override fun showProductDetails(productDetailsInfo: ProductDetailsInfo) {
        viewBinding.productImage.setImageResource(productDetailsInfo.imageId)
        viewBinding.productName.text = productDetailsInfo.name
        viewBinding.productDescription.text = productDetailsInfo.description
        viewBinding.productCost.text = productDetailsInfo.pricesText

        viewBinding.loading.isVisible = false
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        onBackPressedDispatcher.onBackPressed()
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