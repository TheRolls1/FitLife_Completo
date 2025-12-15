package com.fitlife.app.data.local

import android.content.Context

class LocalStorageManager(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("fitlife_prefs", Context.MODE_PRIVATE)

    fun saveValue(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getValue(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
}
