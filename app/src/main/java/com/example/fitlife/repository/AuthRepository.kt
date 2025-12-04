package com.example.fitlife.repository

import com.example.fitlife.data.dto.LoginRequest
import com.example.fitlife.data.dto.LoginResponse
import com.example.fitlife.data.dto.RegisterRequest

interface AuthRepository {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun register(request: RegisterRequest): LoginResponse
    suspend fun logout()
}
