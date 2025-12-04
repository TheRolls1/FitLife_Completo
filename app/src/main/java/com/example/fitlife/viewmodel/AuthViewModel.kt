package com.example.fitlife.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitlife.data.dto.LoginRequest
import com.example.fitlife.data.dto.RegisterRequest
import com.example.fitlife.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = AuthUiState.Error("Completa email y contraseña")
            return
        }
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            try {
                val resp = repo.login(LoginRequest(email, password))
                _uiState.value = AuthUiState.Success(resp.user)
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message ?: "Error de red")
            }
        }
    }

    fun register(nombre: String, email: String, password: String) {
        if (nombre.isBlank() || email.isBlank() || password.isBlank()) {
            _uiState.value = AuthUiState.Error("Completa todos los campos")
            return
        }
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            try {
                val resp = repo.register(RegisterRequest(nombre, email, password))
                _uiState.value = AuthUiState.Success(resp.user)
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message ?: "Error")
            }
        }
    }
}

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val user: Any) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}
