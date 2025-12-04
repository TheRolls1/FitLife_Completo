package com.example.fitlife.ui.screens


import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitlife.data.models.SubscriptionPlan


@Composable
fun PlansScreen(navController: NavController) {
    val plans = listOf(SubscriptionPlan.Basic, SubscriptionPlan.Plus, SubscriptionPlan.Pro)


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Selecciona tu Plan",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            // Este Row ahora se puede desplazar horizontalmente
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()), // Añadido para el desplazamiento
                horizontalArrangement = Arrangement.Center, // Centra las tarjetas
                verticalAlignment = Alignment.Top
            ) {
                plans.forEach { plan ->
                    PlanCard(plan = plan)
                }
            }
        }
    }
}


@Composable
fun PlanCard(plan: SubscriptionPlan) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = plan.name,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = if (plan.price > 0) "$${plan.price}/Mes" else "Gratis",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            plan.features.forEach { feature ->
                Text(
                    text = "• $feature",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* Handle subscription selection */ }) {
                Text(text = "Seleccionar")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlansScreenPreview() {
    // Usamos rememberNavController() para que el Preview funcione correctamente
    PlansScreen(navController = rememberNavController())
}

