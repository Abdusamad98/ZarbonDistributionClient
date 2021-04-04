package com.example.zarbondistributionclient.domain.repositories

import android.util.Log
import com.example.zarbondistributionclient.data.models.loginmodel.LoginData
import com.example.zarbondistributionclient.data.models.loginmodel.LoginResponse
import com.example.zarbondistributionclient.data.sources.local.SAVER
import com.example.zarbondistributionclient.data.sources.remote.retrofit.ApiClient
import com.example.zarbondistributionclient.data.sources.remote.retrofit.LoginApiInterface
import com.example.zarbondistributionclient.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl : LoginRepository {
    private val api = ApiClient.retrofit.create(LoginApiInterface::class.java)

    override suspend fun userLogin(loginData: LoginData): Flow<Result<LoginResponse?>> = flow {
        try {
            val response = api.userLogin("application/json", loginData)
            if (response.code() == 200) {
                emit(Result.success(response.body()))
                SAVER.setPassword(loginData.password)
                SAVER.setLogin(loginData.username)
                SAVER.token = response.body()!!.token!!
                SAVER.setAgentId(response.body()!!.user!!.id)
                SAVER.setFirstName(response.body()!!.user!!.first_name)
                SAVER.setLastName(response.body()!!.user!!.last_name)
                log(response.body()!!.user.toString(), "Login qildi!")
            }

        } catch (e: Exception) {
            Log.d("TTT", "exception = $e" + "Salom")
        }
    }

}