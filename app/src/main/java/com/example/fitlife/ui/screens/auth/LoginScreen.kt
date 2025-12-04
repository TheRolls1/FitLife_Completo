package com.example.fitlife.ui.screens.auth


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitlife.repository.AuthRepositoryImpl
import com.example.fitlife.viewmodel.AuthViewModel
import com.example.fitlife.viewmodel.AuthViewModelFactory
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val authRepo = AuthRepositoryImpl(context)
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(authRepo))
    val uiState by viewModel.uiState.collectAsState()


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Iniciar sesión", style = MaterialTheme.typography.h5)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        Button(onClick = { scope.launch { viewModel.login(email, password) } }, modifier = Modifier.fillMaxWidth()) {
            Text("Entrar")
        }
        Spacer(Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("register") }) { Text("Registrarse") }


        Spacer(Modifier.height(12.dp))
        when (uiState) {
            is com.example.fitlife.viewmodel.AuthUiState.Loading -> CircularProgressIndicator()
            is com.example.fitlife.viewmodel.AuthUiState.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate("home") { popUpTo("login") { inclusive = true } }
                }
            }
            is com.example.fitlife.viewmodel.AuthUiState.Error -> {
                Text((uiState as com.example.fitlife.viewmodel.AuthUiState.Error).message, color = MaterialTheme.colors.error)
            }
            else -> {}
        }
    }
}

