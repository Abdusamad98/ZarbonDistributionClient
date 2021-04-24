package com.example.zarbondistributionclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.utils.spannableText
import kotlinx.android.synthetic.main.item_search_car.view.*

class CarSearchListAdapter :
    ListAdapter<String, CarSearchListAdapter.ViewHolder>(DiffItem) {

    var query = ""

    object DiffItem : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_search_car, parent, false)
    )

    private var listenerClientData: ((String) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.apply {
                carSearchableName.setOnClickListener {
                    listenerClientData?.invoke(
                        getItem(adapterPosition),

                    )
                }
            }
        }
//        if (query != "") productName.text = d.name spannableText query
//        else productName.text = d.name

        fun bind(d: String) {
            itemView.apply {
                if (query != "") carSearchableName.text = d spannableText query
                else carSearchableName.text = d
            }
        }
    }


    fun setOnClientChosenListener(f: (String) -> Unit) {
        listenerClientData = f
    }

}
