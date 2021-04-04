package com.example.zarbondistributionclient.domain.repositories

import com.example.zarbondistributionclient.data.models.loginmodel.LoginData
import com.example.zarbondistributionclient.data.models.loginmodel.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun userLogin(loginData: LoginData): Flow<Result<LoginResponse?>>
}