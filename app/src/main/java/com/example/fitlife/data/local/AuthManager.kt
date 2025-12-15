package com.example.fitlife.data.local

import android.content.Context
import com.example.fitlife.domain.user.AuthUser
import com.example.fitlife.domain.user.UserRole

class AuthManager(context: Context) {

    private val prefs =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun register(email: String, password: String, role: UserRole) {
        prefs.edit()
            .putString("email", email)
            .putString("password", password)
            .putString("role", role.name)
            .apply()
    }

    fun login(email: String, password: String): Boolean {
        val storedEmail = prefs.getString("email", null)
        val storedPassword = prefs.getString("password", null)
        return email == storedEmail && password == storedPassword
    }

    fun recoverPassword(): String? {
        return prefs.getString("password", null)
    }

    fun getCurrentUser(): AuthUser? {
        val email = prefs.getString("email", null) ?: return null
        val password = prefs.getString("password", null) ?: return null
        val roleName = prefs.getString("role", UserRole.USER.name)

        return AuthUser(
            email = email,
            password = password,
            role = UserRole.valueOf(roleName!!)
        )
    }
}
