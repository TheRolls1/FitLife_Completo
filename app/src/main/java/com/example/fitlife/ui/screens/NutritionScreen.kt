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
import com.example.fitlife.data.models.Diet

@Composable
fun NutritionScreen(navController: NavController) {
    val diets = listOf(
        Diet(1, "Aumento de Masa Muscular", "Ideal para quienes buscan incrementar su volumen y fuerza muscular a través de un superávit calórico controlado y alto en proteínas."),
        Diet(2, "Déficit Calórico", "Diseñada para perder peso de forma sostenible, consumiendo menos calorías de las que tu cuerpo gasta."),
        Diet(3, "Quemar Grasa", "Enfocada en maximizar la pérdida de grasa corporal mientras se preserva la masa muscular, combinando nutrición y ejercicio."),
        Diet(4, "Tonificación Muscular", "Ayuda a definir tus músculos y reducir el porcentaje de grasa corporal para un aspecto más marcado y atlético."),
        Diet(5, "Dieta Balanceada", "Un plan de alimentación equilibrado para mantener un estilo de vida saludable, con todos los nutrientes que tu cuerpo necesita."),
        Diet(6, "Dieta Keto (Cetogénica)", "Alta en grasas saludables y muy baja en carbohidratos, induce un estado de cetosis para quemar grasa como energía."),
        Diet(7, "Ayuno Intermitente", "Un patrón de alimentación que alterna entre períodos de ayuno y alimentación, popular por sus beneficios metabólicos.")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Elige tu plan de nutrición", style = MaterialTheme.typography.h5, modifier = Modifier.padding(bottom = 16.dp))
        LazyColumn {
            items(diets) { diet ->
                DietCard(diet = diet, onClick = {
                    // Aquí puedes navegar a una pantalla de detalle de la dieta
                })
            }
        }
    }
}

@Composable
fun DietCard(diet: Diet, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(diet.name, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
            Text(diet.description, style = MaterialTheme.typography.body2)
        }
    }
}
