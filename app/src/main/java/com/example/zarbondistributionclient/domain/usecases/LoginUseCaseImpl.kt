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
    override val errorLogin = MutableLiveData<Unit>()
    override fun userLogin(loginData: LoginData): LiveData<LoginResponse> =
        liveData(Dispatchers.IO) {
            repository.userLogin(loginData).collect {
                if (it.isSuccess) {
                    it.getOrNull()?.let { pair ->
                        if (pair.first == 200) pair.second?.let { it1 -> emit(it1) }
                        if (pair.first == 404) errorLoginLiveData.postValue("Ma'lumot mavjud emas!")

                    }
                } else {
                    errorLogin.postValue(Unit)
                }

            }
        }

}