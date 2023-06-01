package com.example.vehicle_shop_clean.ui.product_list

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehicle_shop_clean.R
import com.example.vehicle_shop_clean.presentation.model.ProductInfo
import com.example.vehicle_shop_clean.presentation.presenter.product_list.ProductListPresenter
import com.example.vehicle_shop_clean.presentation.presenter.product_list.ProductListView
import com.example.vehicle_shop_clean.ui.product_details.ProductDetailsActivity

class ProductListActivity : ComponentActivity(), ProductListView {
    private lateinit var adapter: ProductListAdapter
    private val presenter = ProductListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        adapter = ProductListAdapter(presenter::clickProduct)

        findViewById<RecyclerView>(R.id.product_list)?.let { listView ->
            val layoutManger = GridLayoutManager(this, 2)

            listView.layoutManager = layoutManger
            listView.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()

        presenter.loadData()
    }

    override fun showLoading() {
        findViewById<RecyclerView>(R.id.product_list)?.isVisible = false
        findViewById<ProgressBar>(R.id.loading)?.isVisible = true
    }

    override fun showProducts(products: List<ProductInfo>) {
        adapter.setItems(products)
        findViewById<RecyclerView>(R.id.product_list)?.isVisible = true
        findViewById<ProgressBar>(R.id.loading)?.isVisible = false
    }

    override fun showProductDetails(productId: Int) {
        ProductDetailsActivity.show(this, productId)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}
