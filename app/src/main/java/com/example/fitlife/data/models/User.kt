package com.example.fitlife.data.models

data class User(
    val id: String,
    val nombre: String,
    val email: String,
    val edad: Int? = null,
    val peso: Float? = null,
    val altura: Float? = null,
    val nivelActividad: String? = null
)
