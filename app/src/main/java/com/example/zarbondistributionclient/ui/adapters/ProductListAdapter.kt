package com.example.zarbondistributionclient.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.data.models.productsmodel.ProductData
import com.example.zarbondistributionclient.utils.spannableText
import kotlinx.android.synthetic.main.item_products.view.*

class ProductListAdapter : ListAdapter<ProductData, ProductListAdapter.ViewHolder>(DiffItem) {

    var query = ""

    private var clickListener: ((Int) -> Unit)? = null
    private var clickAboutListener: ((Int) -> Unit)? = null

    object DiffItem : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.product_type == newItem.product_type &&
                    oldItem.unit == newItem.unit &&
                    oldItem.quantity == newItem.quantity &&
                    oldItem.last_update == newItem.last_update &&
                    oldItem.image == newItem.image &&
                    oldItem.provider == newItem.provider
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_products, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.apply {
                productSell.setOnClickListener {
                    clickListener?.invoke(adapterPosition)
                }
                productAbout.setOnClickListener {
                    clickAboutListener?.invoke(getItem(adapterPosition).id)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(d: ProductData) {

            itemView.apply {
                if (query != "") productName.text = d.name spannableText query
                else productName.text = d.name

                productDate.text = d.last_update.substring(0, 10)
                productProvider.text = d.provider
                productPrice.text = d.price.toString()


                if (d.image != "") {
                    Glide.with(product_image.context).load("https://zarbon.herokuapp.com" + d.image)
                        .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                        .into(product_image)
                }

                if (d.product_type.equals("limited")) {
                    productQuantity.text = d.quantity + " " + d.unit
                    productStatus.text = "Vip emas"
                    quantityLinear.visibility = View.VISIBLE
                    productStatus.setTextColor(Color.RED)
                } else if (d.product_type.equals("unlimited")) {
                    quantityLinear.visibility = View.GONE
                    productStatus.text = "Vip"
                    productStatus.setTextColor(Color.BLACK)
                }
            }
        }
    }

    fun clickedProduct(f: (Int) -> Unit) {
        clickListener = f
    }

    fun clickedAboutProduct(f: (Int) -> Unit) {
        clickAboutListener = f
    }

}