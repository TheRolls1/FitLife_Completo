package com.example.fitlife.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitlife.ui.navigation.Screen


@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {


    // Lista de items para la sección de acceso rápido
    val quickAccessItems = listOf(Screen.Nutrition, Screen.Plans, Screen.Progress, Screen.Profile)


    // Usamos LazyColumn para que la pantalla sea desplazable y tenga buen rendimiento.
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp) // Espacio entre secciones
    ) {
        // --- 1. SECCIÓN DE BIENVENIDA ---
        item {
            Header(name = "Usuario") // Aquí podrías pasar el nombre real del usuario
        }


        // --- 2. TARJETA DE ACCIÓN PRINCIPAL (HERO CARD) ---
        item {
            NextWorkoutCard {
                // Navega a la pantalla de entrenamiento al hacer clic
                navController.navigate(Screen.Training.route)
            }
        }


        // --- 3. SECCIÓN DE MÉTRICAS CLAVE ---
        item {
            MetricsDashboard()
        }


        // --- 4. SECCIÓN DE ACCESO RÁPIDO (CATÁLOGO) ---
        item {
            Text(
                text = "Explorar Secciones",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
            )
        }


        // Renderiza las tarjetas de acceso rápido en filas de a dos
        items(quickAccessItems.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (screen in rowItems) {
                    QuickAccessCard(
                        screen = screen,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate(screen.route) }
                    )
                }
            }
        }
    }
}




// --- COMPONENTES REUTILIZABLES PARA EL NUEVO HomeScreen ---


@Composable
fun Header(name: String) {
    Column {
        Text(
            text = "Hola, $name",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "¡Listo para superar tus límites hoy!",
            style = MaterialTheme.typography.subtitle1,
            color = Color.Gray
        )
    }
}


@Composable
fun NextWorkoutCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.FitnessCenter,
                contentDescription = null,
                modifier = Modifier.size(56.dp),
                tint = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = "Próximo Entrenamiento",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = "Rutina de Pierna - 45 min",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f)
                )
            }
        }
    }
}


@Composable
fun MetricsDashboard() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MetricCard(title = "Calorías", value = "750", icon = Icons.Default.LocalFireDepartment, modifier = Modifier.weight(1f))
        MetricCard(title = "Pasos", value = "10,480", icon = Icons.Default.DirectionsWalk, modifier = Modifier.weight(1f))
        MetricCard(title = "Sueño", value = "7h 30m", icon = Icons.Default.Bedtime, modifier = Modifier.weight(1f))
    }
}


@Composable
fun MetricCard(title: String, value: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = title, tint = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
            Text(text = title, style = MaterialTheme.typography.caption)
        }
    }
}


@Composable
fun QuickAccessCard(screen: Screen, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .aspectRatio(1.2f) // Hace que las tarjetas sean más cuadradas
            .clickable(onClick = onClick),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = screen.icon, contentDescription = screen.title, modifier = Modifier.size(36.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = screen.title, style = MaterialTheme.typography.subtitle2, textAlign = TextAlign.Center)
        }
    }
}

