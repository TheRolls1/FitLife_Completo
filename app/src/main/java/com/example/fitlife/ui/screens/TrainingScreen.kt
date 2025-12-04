package com.example.fitlife.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.fitlife.data.models.Workout


@Composable
fun TrainingScreen(navController: NavController) {
    // --- URLs DE IMÁGENES ACTUALIZADAS ---
    // He reemplazado las fotos de ejemplo con imágenes reales de Unsplash.
    val workouts = listOf(
        Workout(1, "Brazos", "Fortalece y tonifica tus bíceps, tríceps y antebrazos.", "https://images.unsplash.com/photo-1581009146145-b5ef050c2e1e?w=400"),
        Workout(2, "Espalda", "Desarrolla una espalda fuerte y mejora tu postura.", "https://images.unsplash.com/photo-1526506118085-60ce8714f8c5?w=400"),
        Workout(3, "Pecho", "Aumenta la fuerza y el volumen de tus músculos pectorales.", "https://images.unsplash.com/photo-1574680096145-d05b474e2155?w=400"),
        Workout(4, "Piernas", "Tonifica cuádriceps, isquiotibiales, glúteos y pantorrillas.", "https://images.unsplash.com/photo-1576678927484-cc907957088c?w=400"),
        Workout(5, "Hombros", "Define tus hombros y mejora la movilidad.", "https://images.unsplash.com/photo-1532029837206-abbe2b7620e3?w=400"),
        Workout(6, "Cuerpo Completo", "Rutinas integrales para todos los grupos musculares.", "https://images.unsplash.com/photo-1541534741688-6078c6bfb5c5?w=400"),
        Workout(7, "Bíceps", "Ejercicios específicos para aislar y desarrollar tus bíceps.", "https://images.unsplash.com/photo-1599332992376-5dd9f25458a0?w=400"), // Específicamente, un curl de bíceps
        Workout(8, "Tríceps", "Enfócate en la parte posterior de tus brazos.", "https://images.unsplash.com/photo-1594882645126-14020914d58d?w=400")
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
        Column {
            // Usamos Coil para cargar la imagen desde la URL
            Image(
                painter = rememberAsyncImagePainter(workout.imageUrl),
                contentDescription = "Imagen de entrenamiento para ${workout.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp), // Damos una altura fija a la imagen
                contentScale = ContentScale.Crop // Hacemos que la imagen cubra el espacio disponible
            )


            // Columna para el texto, con padding
            Column(modifier = Modifier.padding(16.dp)) {
                Text(workout.name, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
                Text(workout.description, style = MaterialTheme.typography.body2)
            }
        }
    }
}

