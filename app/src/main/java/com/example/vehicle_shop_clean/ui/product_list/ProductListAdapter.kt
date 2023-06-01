package com.example.vehicle_shop_clean.ui.product_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vehicle_shop_clean.R
import com.example.vehicle_shop_clean.presentation.model.ProductInfo

class ProductListAdapter(
    private val onProductClick: (productId: Int) -> Unit
) : RecyclerView.Adapter<ProductListViewHolder>() {
    private var productItems: List<ProductInfo> = emptyList()

    fun setItems(items: List<ProductInfo>) {
        productItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_product_list, parent, false)
        return ProductListViewHolder(itemView) { position: Int ->
            if (position != RecyclerView.NO_POSITION) {
                productItems.getOrNull(position)?.let { product ->
                    onProductClick(product.id)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return productItems.size
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        productItems.getOrNull(position)?.let { product ->
            holder.bind(product)
        }
    }
}
