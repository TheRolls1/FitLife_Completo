package com.example.fitlife.data.local

import android.content.Context
import com.example.fitlife.domain.user.UserRole

class RoleManager(context: Context) {

    private val prefs =
        context.getSharedPreferences("fitlife_prefs", Context.MODE_PRIVATE)

    fun saveRole(role: UserRole) {
        prefs.edit().putString("user_role", role.name).apply()
    }

    fun getRole(): UserRole {
        val roleName = prefs.getString("user_role", UserRole.GUEST.name)
        return UserRole.valueOf(roleName!!)
    }
}

