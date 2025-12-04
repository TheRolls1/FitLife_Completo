package com.example.fitlife.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("fitlife_prefs")
private val TOKEN_KEY = stringPreferencesKey("auth_token")

class TokenDataStore(private val context: Context) {
    val tokenFlow = context.dataStore.data.map { prefs -> prefs[TOKEN_KEY] }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs -> prefs[TOKEN_KEY] = token }
    }

    suspend fun clearToken() {
        context.dataStore.edit { prefs -> prefs.remove(TOKEN_KEY) }
    }

    suspend fun readTokenOnce(): String? = context.dataStore.data.map { it[TOKEN_KEY] }.first()
}
