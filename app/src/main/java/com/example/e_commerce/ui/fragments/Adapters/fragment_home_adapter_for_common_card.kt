package com.example.e_commerce.ui.fragments.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce.R
import com.example.e_commerce.ui.DataTypes.ProductType

class fragment_home_adapter_for_common_card(private var productList: List<ProductType>):
    RecyclerView.Adapter<fragment_home_adapter_for_common_card.ProductViewHolder>(){

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.productImage) // You should add an id
        val discountLabel: TextView = itemView.findViewById(R.id.discount_label)
        val price: TextView = itemView.findViewById(R.id.price)
        val discount: TextView = itemView.findViewById(R.id.discount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): fragment_home_adapter_for_common_card.ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.common_cards_component, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.price.text = "$${product.price}"
        holder.discount.text = "$${String.format("%.2f", product.price * (1 - product.discountPercentage / 100))}"
        holder.discountLabel.text = "-${product.discountPercentage.toInt()}%"

        Glide.with(holder.image.context)
            .load(product.thumbnail)
            .into(holder.image)
    }
    fun updateData(newList: List<ProductType>){
        this.productList = newList
    }
    override fun getItemCount(): Int = productList.size
}