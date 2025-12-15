package com.example.fitlife.data.local


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.fitlife.domain.user.UserRole
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore("fitlife_prefs")
private val TOKEN_KEY = stringPreferencesKey("auth_token")
private val ROLE_KEY = stringPreferencesKey("user_role")


// Data class to hold session information
data class SessionData(val token: String?, val role: UserRole?)


class TokenDataStore(private val context: Context) {


    // Flow that emits the current session data
    val sessionFlow: Flow<SessionData> = context.dataStore.data.map { prefs ->
        val token = prefs[TOKEN_KEY]
        val roleString = prefs[ROLE_KEY]
        val role = roleString?.let { UserRole.valueOf(it) }
        SessionData(token, role)
    }


    // Save session data (token and role)
    suspend fun saveSession(token: String, role: UserRole) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
            prefs[ROLE_KEY] = role.name
        }
    }


    // Clear session data
    suspend fun clearSession() {
        context.dataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
            prefs.remove(ROLE_KEY)
        }
    }


    // Read session data once
    suspend fun readSessionOnce(): SessionData {
        val prefs = context.dataStore.data.first()
        val token = prefs[TOKEN_KEY]
        val roleString = prefs[ROLE_KEY]
        val role = roleString?.let { UserRole.valueOf(it) }
        return SessionData(token, role)
    }
}

