package com.example.fitlife.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("FITLIFE_PREFS", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString("JWT_TOKEN", token).apply()
    }

    fun getToken(): String? {
        return prefs.getString("JWT_TOKEN", null)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }
}
