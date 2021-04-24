package com.example.zarbondistributionclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.data.models.productsmodel.Image
import kotlinx.android.synthetic.main.item_about_product.view.*

class AboutProductAdapter :
    ListAdapter<Image, AboutProductAdapter.ViewHolder>(DiffItem) {

    var query = ""

    object DiffItem : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(
            oldItem: Image,
            newItem: Image
        ): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(
            oldItem: Image,
            newItem: Image
        ): Boolean {
            return oldItem.image == newItem.image
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_about_product, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.apply {

            }
        }

        fun bind(d: Image) {
            itemView.apply {

                if (d != null) {
                    Glide.with(aboutProductImage.context)
                        .load("https://zarbon.herokuapp.com" + d.image)
                        .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                        .into(aboutProductImage)
                }
            }
        }
    }


}
