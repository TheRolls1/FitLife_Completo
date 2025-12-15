package com.example.fitlife.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitlife.ui.screens.*


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "menu") {
        composable("menu") { HomeMenuScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("plans") { PlansScreen(navController) }
        composable("training") { TrainingScreen(navController) }
        composable("nutrition") { NutritionScreen(navController) }
        composable("progress") { ProgressScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("login") { com.example.fitlife.ui.screens.auth.LoginScreen(navController) }
        composable("register") { com.example.fitlife.ui.screens.auth.RegisterScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}


