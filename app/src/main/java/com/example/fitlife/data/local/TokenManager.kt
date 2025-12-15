package com.example.fitlife.data.local


import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.fitlife.App
import com.example.fitlife.domain.user.UserRole
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


object TokenManager {
    // Expose role as a MutableState to be observed by Compose
    var userRole: MutableState<UserRole?> = mutableStateOf(null)
        private set


    @Volatile
    private var token: String? = null


    private var initialized = false


    // Initialize the session from DataStore
    fun init(context: Context, scope: CoroutineScope) {
        if (initialized.not()) {
            initialized = true
            val store = TokenDataStore(context)
            scope.launch {
                val session = store.readSessionOnce()
                token = session.token
                userRole.value = session.role
            }
        }
    }


    // Save a new session
    fun setSession(token: String, role: UserRole, context: Context) {
        TokenManager.token = token
        userRole.value = role // Update the state
        val store = TokenDataStore(context)
        App.appScope.launch {
            store.saveSession(token, role)
        }
    }


    // Clear the current session
    fun clearSession(context: Context) {
        token = null
        userRole.value = null // Update the state
        val store = TokenDataStore(context)
        App.appScope.launch {
            store.clearSession()
        }
    }


    fun getToken(): String? = token


    // This is no longer the primary way to get the role in compose
    fun getUserRole(): UserRole? = userRole.value
}

