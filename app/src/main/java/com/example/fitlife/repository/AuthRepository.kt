package com.example.fitlife.data.repository

import com.example.fitlife.data.model.LoginRequest
import com.example.fitlife.data.network.ApiService

class AuthRepository(private val api: ApiService) {

    suspend fun login(email: String, password: String) =
        api.login(LoginRequest(email, password))

    suspend fun getProfile() = api.getProfile()
}

