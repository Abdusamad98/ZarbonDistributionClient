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

    override suspend fun userLogin(loginData: LoginData): Flow<Result<Pair<Int,LoginResponse?>>> = flow {
        try {
            val response = api.userLogin("application/json", loginData)
            if (response.code() == 200) {
                emit(Result.success(Pair(200,response.body())))
                SAVER.setPhoneNumber(loginData.phone_number1)
                SAVER.setLogin(loginData.name)

                SAVER.setAgentId(response!!.body()!!.agent_id)
                SAVER.setClientId(response.body()!!.client_id)
            }
            else if(response.code() == 404){
                emit(Result.success(Pair(404,null)))
            }

        } catch (e: Exception) {
            Log.d("TTT", "exception = $e" + "Salom")
        }
    }

}