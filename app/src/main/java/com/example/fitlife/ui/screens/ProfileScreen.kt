package com.example.fitlife.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun ProfileScreen(navController: NavController) {
    val settingsOptions = listOf(
        "Progreso",
        "Entrenamiento",
        "Privacidad",
        "Ayuda"
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- Sección de Cabecera del Perfil ---
        Spacer(modifier = Modifier.height(32.dp))


        // Avatar
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = "Avatar de Perfil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )


        Spacer(modifier = Modifier.height(16.dp))


        // Nombre y correo del usuario
        Text(
            text = "Nombre de Usuario",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "usuario@email.com",
            style = MaterialTheme.typography.body1,
            color = Color.Gray
        )


        Spacer(modifier = Modifier.height(24.dp))


        // Botón de Editar Perfil
        Button(
            onClick = { /* TODO: Navegar a pantalla de edición de perfil */ },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Editar Perfil")
        }


        Divider(modifier = Modifier.padding(vertical = 24.dp))


        // --- Sección de Ajustes ---
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Ocupa el espacio restante
        ) {
            items(settingsOptions) { option ->
                SettingsItem(text = option, onClick = { /* TODO: Implementar acción */ })
            }
        }


        // --- Botón de Cerrar Sesión ---
        Button(
            onClick = { /* TODO: Lógica para cerrar sesión */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Cerrar Sesión",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Cerrar Sesión", color = Color.White)
        }
    }
}


@Composable
fun SettingsItem(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text, style = MaterialTheme.typography.body1, fontSize = 18.sp)
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Ir a $text",
            tint = Color.Gray
        )
    }
}




@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}
