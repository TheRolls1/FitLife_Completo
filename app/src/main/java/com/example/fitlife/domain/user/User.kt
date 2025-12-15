package com.example.fitlife.data.dto


import com.example.fitlife.domain.user.UserRole


data class User(
    val id: Int,
    val nombre: String,
    val email: String,
    val role: UserRole
)



