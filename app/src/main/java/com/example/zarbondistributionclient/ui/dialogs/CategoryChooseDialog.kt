package com.example.zarbondistributionclient.ui.dialogs

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.data.models.categorymodel.CategoryData
import com.example.zarbondistributionclient.ui.adapters.CategoryListAdapter
import kotlinx.android.synthetic.main.category_dialog.view.*


class CategoryChooseDialog(context: Context, categories: List<CategoryData>) :
    BaseDialog(context, R.layout.category_dialog) {
    private var listener: ((Int) -> Unit)? = null
    private var adapter = CategoryListAdapter(categories)


    init {
        view.apply {

            recyclerCategories.adapter = adapter
            recyclerCategories.layoutManager = LinearLayoutManager(context)

            adapter.setOnCategoryChosen { data ->
                listener?.invoke(data)
                close()
            }
        }
    }

    fun setOnCategoryChosen(f: ((Int) -> Unit)?) {
        listener = f
    }
}

