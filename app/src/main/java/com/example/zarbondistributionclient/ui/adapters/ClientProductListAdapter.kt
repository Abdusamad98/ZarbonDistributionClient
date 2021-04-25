package com.example.zarbondistributionclient.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.data.models.clientproducts.ClientProducts
import kotlinx.android.synthetic.main.item_client_products.view.*


class ClientProductListAdapter :
    ListAdapter<ClientProducts, ClientProductListAdapter.ViewHolder>(DiffItem) {

    object DiffItem : DiffUtil.ItemCallback<ClientProducts>() {
        override fun areItemsTheSame(
            oldItem: ClientProducts,
            newItem: ClientProducts,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ClientProducts,
            newItem: ClientProducts,
        ): Boolean {
            return oldItem.created_date == newItem.created_date &&
                    oldItem.price == newItem.price &&
                    oldItem.product == newItem.product &&
                    oldItem.quantity == newItem.quantity
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_client_products, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.apply {

            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(d: ClientProducts) {
            itemView.apply {
                soldItemNameClient.text = d.product.name
                givenDiscount.text = d.discount + " %"
                productDebt.text = d.debt

                if (d.status.equals("ordered")) {
                    soldItemStatusClient.text = "Buyurtma qilindi"
                    soldItemStatusClient.setTextColor(Color.RED)
                } else if (d.status.equals("delivered")) {
                    soldItemStatusClient.text = "Yetkazildi"
                    soldItemStatusClient.setTextColor(Color.GREEN)
                }
                soldItemQuantityClient.text = d.quantity + " " + d.product.unit
                soldItemPriceClient.text = d.price.price
                soldItemDateClient.text = d.created_date.substring(0, 10)

            }
        }
    }


}