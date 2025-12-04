package com.example.fitlife.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitlife.data.models.Diet


@Composable
fun NutritionScreen(navController: NavController) {
    // Lista de dietas con un icono asociado para cada una
    val diets = listOf(
        Diet(1, "Aumento de Masa Muscular", "Ideal para quienes buscan incrementar su volumen y fuerza muscular.", Icons.Default.FitnessCenter),
        Diet(2, "Déficit Calórico", "Diseñada para perder peso de forma sostenible.", Icons.Default.ArrowDownward),
        Diet(3, "Quemar Grasa", "Enfocada en maximizar la pérdida de grasa corporal.", Icons.Default.LocalFireDepartment),
        Diet(4, "Tonificación Muscular", "Ayuda a definir tus músculos y reducir la grasa corporal.", Icons.Default.Bolt),
        Diet(5, "Dieta Balanceada", "Un plan de alimentación equilibrado para un estilo de vida saludable.", Icons.Default.Balance),
        Diet(6, "Dieta Keto (Cetogénica)", "Alta en grasas saludables y muy baja en carbohidratos.", Icons.Default.Restaurant),
        Diet(7, "Ayuno Intermitente", "Alterna entre períodos de ayuno y alimentación.", Icons.Default.Timer)
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
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically // Alinea el icono y el texto verticalmente
        ) {
            // Añadimos el icono al inicio de la tarjeta
            Icon(
                imageVector = diet.icon,
                contentDescription = "Icono de ${diet.name}", // Descripción para accesibilidad
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colors.primary
            )


            Spacer(modifier = Modifier.width(16.dp)) // Espacio entre el icono y el texto


            Column {
                Text(diet.name, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
                Text(diet.description, style = MaterialTheme.typography.body2)
            }
        }
    }
}

