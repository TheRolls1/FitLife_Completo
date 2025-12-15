package com.example.fitlife.domain.user

data class AuthUser(
    val email: String,
    val password: String,
    val role: UserRole
)
