package com.example.fitlife.repository

import android.content.Context
import com.example.fitlife.data.dto.LoginRequest
import com.example.fitlife.data.dto.LoginResponse
import com.example.fitlife.data.dto.RegisterRequest
import com.example.fitlife.data.local.TokenManager
import com.example.fitlife.data.local.TokenDataStore
import com.example.fitlife.data.remote.ApiService
import com.example.fitlife.data.remote.RetrofitClient

class AuthRepositoryImpl(private val context: Context) : AuthRepository {
    private val baseUrl = "http://10.0.2.2:3000"
    private val api: ApiService by lazy { RetrofitClient.getClient(baseUrl, context).create(ApiService::class.java) }
    private val tokenStore = TokenDataStore(context)

    override suspend fun login(request: LoginRequest): LoginResponse {
        val resp = api.login(request)
        TokenManager.setToken(resp.token, context)
        return resp
    }

    override suspend fun register(request: RegisterRequest): LoginResponse {
        val resp = api.register(request)
        TokenManager.setToken(resp.token, context)
        return resp
    }

    override suspend fun logout() {
        TokenManager.setToken(null, context)
    }
}
