package com.example.fitlife.repository

import com.example.fitlife.data.dto.*

interface AuthRepository {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun register(request: RegisterRequest): LoginResponse
    suspend fun logout()
}
