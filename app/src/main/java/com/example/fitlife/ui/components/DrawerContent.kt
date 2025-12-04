package com.example.fitlife.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(onNavigate: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Inicio", modifier = Modifier.clickable { onNavigate("home") })
        Text("Planes", modifier = Modifier.padding(top = 8.dp).clickable { onNavigate("plans") })
        Text("Entrenamiento", modifier = Modifier.padding(top = 8.dp).clickable { onNavigate("training") })
        Text("Nutrición", modifier = Modifier.padding(top = 8.dp).clickable { onNavigate("nutrition") })
        Text("Progreso", modifier = Modifier.padding(top = 8.dp).clickable { onNavigate("progress") })
        Text("Perfil", modifier = Modifier.padding(top = 8.dp).clickable { onNavigate("profile") })
        Text("Iniciar sesión", modifier = Modifier.padding(top = 16.dp).clickable { onNavigate("login") })
    }
}
