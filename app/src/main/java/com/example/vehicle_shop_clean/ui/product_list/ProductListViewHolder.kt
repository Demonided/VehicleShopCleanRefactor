package com.example.vehicle_shop_clean.ui.product_list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vehicle_shop_clean.R
import com.example.vehicle_shop_clean.presentation.model.ProductInfo

class ProductListViewHolder(
    itemView: View,
    onProductClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            onProductClick(bindingAdapterPosition)
        }
    }

    fun bind(product: ProductInfo) {
        itemView.findViewById<ImageView>(R.id.product_image)?.setImageResource(product.imageId)
        itemView.findViewById<TextView>(R.id.product_name)?.text = product.name
        itemView.findViewById<TextView>(R.id.product_price)?.text = product.priceText
    }
}
