package com.example.fitlife.data.models


// No necesitas ninguna importación extra para este cambio


data class Workout(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String // <-- ¡AÑADE ESTA LÍNEA!
)

