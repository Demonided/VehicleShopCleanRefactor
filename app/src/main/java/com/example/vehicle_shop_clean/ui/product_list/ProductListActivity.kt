package com.example.vehicle_shop_clean.ui.product_list

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehicle_shop_clean.R
import com.example.vehicle_shop_clean.databinding.ActivityProductListBinding
import com.example.vehicle_shop_clean.presentation.model.ProductInfo
import com.example.vehicle_shop_clean.presentation.presenter.product_list.ProductListPresenter
import com.example.vehicle_shop_clean.presentation.presenter.product_list.ProductListView
import com.example.vehicle_shop_clean.ui.product_details.ProductDetailsActivity

class ProductListActivity : ComponentActivity(), ProductListView {
    private lateinit var adapter: ProductListAdapter
    private val presenter = ProductListPresenter(this)
    private lateinit var viewBinding: ActivityProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        adapter = ProductListAdapter(presenter::clickProduct)
        val layoutManger = GridLayoutManager(this, 2)

        viewBinding.productList.layoutManager = layoutManger
        viewBinding.productList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        presenter.loadData()
    }

    override fun showLoading() {
        viewBinding.productList.isVisible = false
        viewBinding.loading.isVisible = true
    }

    override fun showProducts(products: List<ProductInfo>) {
        adapter.setItems(products)
        viewBinding.productList.isVisible = true
        viewBinding.loading.isVisible = false
    }

    override fun showProductDetails(productId: Int) {
        ProductDetailsActivity.show(this, productId)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}
