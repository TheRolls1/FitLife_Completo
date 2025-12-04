package com.example.fitlife.data.models


import androidx.compose.ui.graphics.vector.ImageVector


// Asegúrate de que tu clase 'Diet' tenga esta estructura
data class Diet(
    val id: Int,
    val name: String,
    val description: String,
    val icon: ImageVector // <-- Añade esta línea para el icono
)
