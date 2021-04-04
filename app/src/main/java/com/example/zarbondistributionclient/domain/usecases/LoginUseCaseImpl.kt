package com.example.zarbondistributionclient.domain.usecases
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionclient.data.models.loginmodel.LoginData
import com.example.zarbondistributionclient.data.models.loginmodel.LoginResponse
import com.example.zarbondistributionclient.domain.repositories.LoginRepository
import com.example.zarbondistributionclient.domain.repositories.LoginRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect


class LoginUseCaseImpl : LoginUseCase {
    private val repository: LoginRepository = LoginRepositoryImpl()
    override val errorLoginLiveData = MutableLiveData<String>()

    override fun userLogin(loginData: LoginData): LiveData<LoginResponse> =
        liveData(Dispatchers.IO) {
            repository.userLogin(loginData).collect {
                if (it.isSuccess) emit(it.getOrNull()!!)
                else errorLoginLiveData.postValue("Error")
            }
        }

}