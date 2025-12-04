package com.example.fitlife.data.local

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object TokenManager {
    @Volatile
    private var token: String? = null
    private var initialized = false

    fun init(context: Context, scope: CoroutineScope) {
        if (!initialized) {
            initialized = true
            val store = TokenDataStore(context)
            scope.launch {
                val t = store.readTokenOnce()
                token = t
            }
        }
    }

    fun setToken(newToken: String?, context: Context) {
        token = newToken
        val store = TokenDataStore(context)
        com.example.fitlife.App.appScope.launch {
            if (newToken != null) store.saveToken(newToken) else store.clearToken()
        }
    }

    fun getToken(): String? = token
}
