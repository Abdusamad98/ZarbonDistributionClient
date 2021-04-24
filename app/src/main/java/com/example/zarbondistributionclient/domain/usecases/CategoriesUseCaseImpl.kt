package com.example.zarbondistributionclient.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionclient.data.models.categorymodel.CategoryData
import com.example.zarbondistributionclient.domain.repositories.CategoryRepository
import com.example.zarbondistributionclient.domain.repositories.CategoryRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class CategoriesUseCaseImpl : CategoriesUseCase {

    private val repository: CategoryRepository = CategoryRepositoryImpl()
    override val errorCategoriesLiveData = MutableLiveData<Unit>()

    override fun getCategories(): LiveData<List<CategoryData>> = liveData(Dispatchers.IO) {
        repository.getCategories().collect {
            if (it.isSuccess) {
                emit(it.getOrNull()!!)
            } else {
                errorCategoriesLiveData.postValue(Unit)
            }
        }
    }
}
