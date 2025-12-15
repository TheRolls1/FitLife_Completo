package com.example.fitlife.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun SettingsScreen(navController: NavController) {
    // Estado de ejemplo para el interruptor de notificaciones
    var areNotificationsEnabled by remember { mutableStateOf(true) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Para asegurar que quepa en pantallas pequeñas
    ) {
        Text(
            text = "Configuraciones",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )


        // Categoría de Opciones Generales
        SettingsCategory(title = "General")
        SettingSwitchItem(
            title = "Notificaciones",
            icon = Icons.Default.Notifications,
            checked = areNotificationsEnabled,
            onCheckedChange = { areNotificationsEnabled = it }
        )


        // Categoría de Cuenta
        Spacer(modifier = Modifier.height(16.dp))
        SettingsCategory(title = "Cuenta")
        SettingClickableItem(
            title = "Editar Perfil",
            icon = Icons.Default.Edit,
            onClick = { navController.navigate("profile") }
        )
        SettingClickableItem(
            title = "Cerrar Sesión",
            icon = Icons.Default.ExitToApp,
            onClick = {
                // Lógica para cerrar sesión
                // navController.navigate("login") { popUpTo(navController.graph.startDestinationId) { inclusive = true } }
            }
        )


        // Categoría "Ayuda y Soporte"
        Spacer(modifier = Modifier.height(16.dp))
        SettingsCategory(title = "Ayuda y Soporte")
        SettingClickableItem(
            title = "Centro de Ayuda",
            icon = Icons.Default.Help,
            onClick = { /* TODO: Navegar al centro de ayuda */ }
        )
        SettingClickableItem(
            title = "Contáctanos",
            icon = Icons.Default.Email,
            onClick = { /* TODO: Abrir cliente de email */ }
        )


        // Categoría "Legal y Acerca de"
        Spacer(modifier = Modifier.height(16.dp))
        SettingsCategory(title = "Legal y Acerca de")
        SettingClickableItem(
            title = "Política de Privacidad",
            icon = Icons.Default.Description,
            onClick = { /* TODO: Navegar a la pantalla de Política de Privacidad */ }
        )
        SettingClickableItem(
            title = "Términos de Servicio",
            icon = Icons.Default.Gavel,
            onClick = { /* TODO: Navegar a la pantalla de Términos de Servicio */ }
        )
        SettingClickableItem(
            title = "Versión de la App",
            subtitle = "1.0.0", // Puedes obtener esto dinámicamente
            icon = Icons.Default.Info,
            onClick = {} // Sin acción
        )


        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
private fun SettingsCategory(title: String) {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
        )
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}


@Composable
private fun SettingSwitchItem(
    title: String,
    icon: ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = title, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, fontSize = 16.sp)
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}


@Composable
private fun SettingClickableItem(
    title: String,
    subtitle: String? = null,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = title, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 16.sp)
            if (subtitle != null) {
                Text(text = subtitle, fontSize = 14.sp, color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f))
            }
        }
        if (subtitle == null) {
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null, tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f))
        }
    }
}
