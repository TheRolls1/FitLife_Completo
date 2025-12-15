package com.example.fitlife.ui.session

import androidx.lifecycle.ViewModel
import com.example.fitlife.data.local.RoleManager
import com.example.fitlife.domain.user.UserRole

class SessionViewModel(
    private val roleManager: RoleManager
) : ViewModel() {

    fun setRole(role: UserRole) {
        roleManager.saveRole(role)
    }

    fun getCurrentRole(): UserRole {
        return roleManager.getRole()
    }
}

