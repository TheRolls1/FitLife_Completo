package com.example.fitlife.data.dto

data class LoginRequest(val email: String, val password: String)
data class RegisterRequest(val nombre: String, val email: String, val password: String)
data class LoginResponse(val token: String, val user: UserDto)
data class UserDto(val id: String, val nombre: String, val email: String)

data class PlanDto(
    val id: String,
    val titulo: String,
    val descripcion: String,
    val nivel: String,
    val tipo: String,
    val duracionMinutos: Int,
    val precioCLP: Int
)

data class UploadResponse(val url: String)
