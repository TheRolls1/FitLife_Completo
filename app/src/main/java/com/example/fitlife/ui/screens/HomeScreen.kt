package com.example.fitlife.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(navController: androidx.navigation.NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Inicio - Resumen diario", style = androidx.compose.material.MaterialTheme.typography.h5)
    }
}
