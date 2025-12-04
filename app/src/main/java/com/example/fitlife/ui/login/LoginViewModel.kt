package com.example.fitlife.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitlife.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = authRepository.login(email, password)
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
