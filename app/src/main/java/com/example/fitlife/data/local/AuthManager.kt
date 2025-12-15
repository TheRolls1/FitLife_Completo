package com.example.fitlife.data.local

import android.content.Context
import com.example.fitlife.domain.user.DefaultCredentials
import com.example.fitlife.domain.user.UserRole

class AuthManager(context: Context) {

    private val prefs =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    // 🔐 LOGIN
    fun login(email: String, password: String): UserRole? {

        // 1️⃣ Revisar usuario registrado
        val savedEmail = prefs.getString("email", null)
        val savedPassword = prefs.getString("password", null)
        val savedRole = prefs.getString("role", null)

        if (
            email == savedEmail &&
            password == savedPassword &&
            savedRole != null
        ) {
            return UserRole.valueOf(savedRole)
        }

        // 2️⃣ Revisar credenciales por defecto
        DefaultCredentials.credentials.forEach { (role, pair) ->
            if (email == pair.first && password == pair.second) {
                saveSession(role)
                return role
            }
        }

        return null
    }

    // 📝 REGISTRO
    fun register(email: String, password: String, role: UserRole) {
        prefs.edit()
            .putString("email", email)
            .putString("password", password)
            .putString("role", role.name)
            .apply()
    }

    // 💾 SESIÓN
    private fun saveSession(role: UserRole) {
        prefs.edit().putString("role", role.name).apply()
    }

    fun getCurrentRole(): UserRole {
        val role = prefs.getString("role", UserRole.GUEST.name)
        return UserRole.valueOf(role!!)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }
}

