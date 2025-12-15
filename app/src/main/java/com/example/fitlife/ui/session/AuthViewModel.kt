package com.example.fitlife.ui.session

import androidx.lifecycle.ViewModel
import com.example.fitlife.data.local.AuthManager
import com.example.fitlife.domain.user.UserRole

class AuthViewModel(
    private val authManager: AuthManager
) : ViewModel() {

    fun login(email: String, password: String): UserRole? {
        return authManager.login(email, password)
    }

    fun register(email: String, password: String, role: UserRole) {
        authManager.register(email, password, role)
    }

    fun getCurrentRole(): UserRole {
        return authManager.getCurrentRole()
    }

    fun logout() {
        authManager.logout()
    }
}

