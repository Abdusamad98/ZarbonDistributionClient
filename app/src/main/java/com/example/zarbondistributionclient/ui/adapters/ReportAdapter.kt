package com.example.zarbondistributionclient.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.data.models.reportmodel.ReportData
import com.example.zarbondistributionclient.utils.spannableText
import kotlinx.android.synthetic.main.item_report.view.*

class ReportAdapter : ListAdapter<ReportData, ReportAdapter.ViewHolder>(DiffItem) {

    var query = ""

    private var clickListener : ((Int) -> Unit)? = null
    object DiffItem : DiffUtil.ItemCallback<ReportData>() {
        override fun areItemsTheSame(oldItem: ReportData, newItem: ReportData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ReportData, newItem: ReportData): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.date == newItem.date &&
                    oldItem.product_image == newItem.product_image&&
                    oldItem.debt == newItem.debt&&
                    oldItem.quantity == newItem.quantity
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_report, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.apply {
//                productSell.setOnClickListener {
//                    clickListener?.invoke(getItem(adapterPosition).id)
//                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(d: ReportData) {

            itemView.apply {
                if (query != "") productName.text = d.name spannableText query
                else productName.text = d.name

                productDate.text = d.date//.substring(0,10)
                productQuantity.text = d.quantity.toString() + " litr"
                productDebt.text = d.debt.toString() + " so'm"

            }
        }
    }

    fun clickedProduct(f : (Int )->Unit) {
        clickListener = f
    }

}