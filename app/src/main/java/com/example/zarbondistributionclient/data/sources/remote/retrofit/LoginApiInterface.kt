package com.example.zarbondistributionclient.data.sources.remote.retrofit


import com.example.zarbondistributionclient.data.models.loginmodel.LoginData
import com.example.zarbondistributionclient.data.models.loginmodel.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApiInterface {

    @POST("user/login/")
   suspend fun userLogin(
        @Header("Accept") app_json: String,
        @Body loginData: LoginData
    ) : Response<LoginResponse>

}