package com.example.fitlife.ui.session

import androidx.lifecycle.ViewModel
import com.example.fitlife.data.local.AuthManager
import com.example.fitlife.domain.user.UserRole

class AuthViewModel(
    private val authManager: AuthManager
) : ViewModel() {

    fun register(email: String, password: String, role: UserRole) {
        authManager.register(email, password, role)
    }

    fun login(email: String, password: String): Boolean {
        return authManager.login(email, password)
    }

    fun recoverPassword(): String? {
        return authManager.recoverPassword()
    }
}
