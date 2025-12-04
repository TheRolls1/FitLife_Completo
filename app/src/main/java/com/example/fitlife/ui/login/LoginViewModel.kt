package com.example.fitlife.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitlife.data.dto.LoginRequest
import com.example.fitlife.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                authRepository.login(LoginRequest(email, password))
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
