package com.example.zarbondistributionclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.data.models.categorymodel.CategoryData
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryListAdapter(val data: List<CategoryData>) :
    RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.category_item, parent, false
        )
    )

    private var listener: ((Int) -> Unit)? = null

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            view.apply {
                categoryName.setOnClickListener {
                    listener?.invoke(data[adapterPosition].id)
                }
            }
        }


        fun bind() {
            val d = data[adapterPosition]
            itemView.apply {
                categoryName.text = d.name
            }
        }
    }

    fun setOnCategoryChosen(f: ((Int) -> Unit)?) {
        listener = f
    }

}
