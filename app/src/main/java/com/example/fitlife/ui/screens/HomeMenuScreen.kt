package com.example.fitlife.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun HomeMenuScreen(navController: NavController) {
    // Usamos LazyColumn para un rendimiento óptimo y fácil espaciado.
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio automático entre cada elemento
    ) {
        // Encabezado de bienvenida
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Bienvenido a FitLife SPA",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Tu centro de bienestar digital. ¿Qué te gustaría hacer hoy?",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }


        // Tarjetas del menú
        item {
            MenuCard(
                title = "Iniciar Sesión / Registrarse",
                subtitle = "Accede a tu perfil y guarda tu progreso.",
                icon = Icons.Default.Login,
                onClick = { navController.navigate("login") }
            )
        }
        item {
            MenuCard(
                title = "Ver Planes",
                subtitle = "Descubre nuestras suscripciones y beneficios.",
                icon = Icons.Default.Star,
                onClick = { navController.navigate("plans") }
            )
        }
        item {
            MenuCard(
                title = "Entrenamiento",
                subtitle = "Explora rutinas y ejercicios personalizados.",
                icon = Icons.Default.FitnessCenter,
                onClick = { navController.navigate("training") }
            )
        }
    }
}


/**
 * Un Composable reutilizable para crear tarjetas de menú atractivas.
 */
@Composable
fun MenuCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick), // Hacemos toda la tarjeta clickeable
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null, // El texto ya describe la acción
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

