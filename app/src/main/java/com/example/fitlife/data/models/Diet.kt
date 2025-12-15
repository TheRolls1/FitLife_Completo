package com.example.fitlife.data.models


import androidx.compose.ui.graphics.vector.ImageVector



data class Diet(
    val id: Int,
    val name: String,
    val description: String,
    val icon: ImageVector
)
