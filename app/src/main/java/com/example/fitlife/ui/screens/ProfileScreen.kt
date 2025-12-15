package com.example.fitlife.ui.screens


import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.fitlife.viewmodel.ProfileViewModel
import java.io.File


@Composable
fun ProfileScreen(navController: NavController, profileViewModel: ProfileViewModel = viewModel()) {
    val context = LocalContext.current
    val settingsOptions = listOf("Progreso", "Entrenamiento", "Privacidad", "Ayuda")


    // Launcher for selecting an image from the gallery
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> profileViewModel.onImageSelected(uri) }
    )


    // Launcher for taking a picture with the camera
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                profileViewModel.onImageSelected(profileViewModel.imageUri.value)
            }
        }
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))


        // --- Profile Avatar ---
        Image(
            painter = rememberAsyncImagePainter(model = profileViewModel.imageUri.value ?: ""),
            contentDescription = "Avatar de Perfil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )


        Spacer(modifier = Modifier.height(16.dp))


        // --- User Name and Email ---
        Text(text = "Nombre de Usuario", style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
        Text(text = "usuario@email.com", style = MaterialTheme.typography.body1, color = Color.Gray)


        Spacer(modifier = Modifier.height(24.dp))


        // --- NATIVE RESOURCE BUTTONS ---
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                val uri = createImageUri(context)
                profileViewModel.onImageSelected(uri)
                cameraLauncher.launch(uri)
            }) {
                Icon(Icons.Default.CameraAlt, contentDescription = "Tomar Foto")
                Spacer(Modifier.width(8.dp))
                Text("Cámara")
            }


            Button(onClick = { galleryLauncher.launch("image/*") }) {
                Icon(Icons.Default.PhotoLibrary, contentDescription = "Elegir de Galería")
                Spacer(Modifier.width(8.dp))
                Text("Galería")
            }
        }


        Spacer(modifier = Modifier.height(16.dp))


        // --- Edit Profile Button ---
        Button(onClick = { /* TODO: Navigate to edit profile screen */ }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("Editar Perfil")
        }


        Divider(modifier = Modifier.padding(vertical = 24.dp))


        // --- Settings List ---
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(settingsOptions) { option ->
                SettingsItem(text = option, onClick = { /* TODO: Implement action */ })
            }
        }


        // --- Logout Button ---
        Button(
            onClick = { /* TODO: Logout logic */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar Sesión", tint = Color.White)
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
        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Ir a $text", tint = Color.Gray)
    }
}


private fun createImageUri(context: Context): Uri {
    val imageFile = File.createTempFile("JPEG_${System.currentTimeMillis()}_", ".jpg", context.cacheDir)
    // Use the authority that matches the Manifest declaration EXACTLY
    val authority = "com.example.fitlife.provider"
    return FileProvider.getUriForFile(context, authority, imageFile)
}

