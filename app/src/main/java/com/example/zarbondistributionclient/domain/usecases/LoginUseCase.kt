package com.example.zarbondistributionclient.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionclient.data.models.loginmodel.LoginData
import com.example.zarbondistributionclient.data.models.loginmodel.LoginResponse

interface LoginUseCase {
    val errorLoginLiveData : LiveData<String>
    val errorLogin : LiveData<Unit>
    fun userLogin(loginData: LoginData) : LiveData<LoginResponse>
}