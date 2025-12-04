package com.example.fitlife.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitlife.data.models.Workout

@Composable
fun TrainingScreen(navController: NavController) {
    val workouts = listOf(
        Workout(1, "Brazos", "Fortalece y tonifica tus bíceps, tríceps y antebrazos."),
        Workout(2, "Espalda", "Desarrolla una espalda fuerte y mejora tu postura con estos ejercicios."),
        Workout(3, "Pecho", "Aumenta la fuerza y el volumen de tus músculos pectorales."),
        Workout(4, "Piernas", "Tonifica tus cuádriceps, isquiotibiales, glúteos y pantorrillas."),
        Workout(5, "Hombros", "Define tus hombros y mejora la movilidad de la parte superior del cuerpo."),
        Workout(6, "Cuerpo Completo", "Rutinas integrales para trabajar todos los grupos musculares en una sola sesión."),
        Workout(7, "Bíceps", "Ejercicios específicos para aislar y desarrollar tus bíceps."),
        Workout(8, "Tríceps", "Enfócate en la parte posterior de tus brazos para mayor fuerza y definición.")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Elige tu entrenamiento", style = MaterialTheme.typography.h5, modifier = Modifier.padding(bottom = 16.dp))
        LazyColumn {
            items(workouts) { workout ->
                WorkoutCard(workout = workout, onClick = {
                    // Aquí puedes navegar a una pantalla de detalle del entrenamiento
                })
            }
        }
    }
}

@Composable
fun WorkoutCard(workout: Workout, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(workout.name, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
            Text(workout.description, style = MaterialTheme.typography.body2)
        }
    }
}
