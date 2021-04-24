package com.example.zarbondistributionclient.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionclient.data.models.categorymodel.CategoryData

interface CategoriesUseCase {
    val errorCategoriesLiveData: LiveData<Unit>
    fun getCategories(): LiveData<List<CategoryData>>
}