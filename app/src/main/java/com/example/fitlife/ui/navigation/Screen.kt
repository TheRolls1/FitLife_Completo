package com.example.fitlife.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : Screen("home", "Inicio", Icons.Default.Home)
    object Nutrition : Screen("nutrition", "Nutrición", Icons.Default.Fastfood)
    object Plans : Screen("plans", "Planes", Icons.Default.ListAlt)
    object Progress : Screen("progress", "Progreso", Icons.Default.TrendingUp)
    object Profile : Screen("profile", "Perfil", Icons.Default.Person)
    object Training : Screen("training", "Entrenamiento", Icons.Default.FitnessCenter)
}