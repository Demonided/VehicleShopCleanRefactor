package com.example.vehicle_shop_clean.ui.product_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import com.example.vehicle_shop_clean.R
import com.example.vehicle_shop_clean.presentation.model.ProductDetailsInfo
import com.example.vehicle_shop_clean.presentation.presenter.product_details.ProductDetailsPresenter
import com.example.vehicle_shop_clean.presentation.presenter.product_details.ProductDetailsView

class ProductDetailsActivity : ComponentActivity(), ProductDetailsView {

    private val presenter = ProductDetailsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

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
        findViewById<ImageView>(R.id.product_image)?.let { imageView ->
            imageView.setImageResource(productDetailsInfo.imageId)
        }

        findViewById<TextView>(R.id.product_name)?.let { textView ->
            textView.text = productDetailsInfo.name
        }

        findViewById<TextView>(R.id.product_description)?.let { textView ->
            textView.text = productDetailsInfo.description
        }

        findViewById<TextView>(R.id.product_cost)?.let { textView ->
            textView.text = productDetailsInfo.pricesText
        }

        findViewById<FrameLayout>(R.id.loading)?.let { progressBar ->
            progressBar.isVisible = false
        }
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