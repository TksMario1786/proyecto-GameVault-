package com.example.gamevault.data.local

import android.content.Context
import android.content.SharedPreferences

interface SessionManager {
    fun saveToken(token: String)
    fun getToken(): String?
    fun clearSession()
}

class SessionManagerImpl(context: Context) : SessionManager {
    private val prefs: SharedPreferences = context.getSharedPreferences("gamevault_prefs", Context.MODE_PRIVATE)

    override fun saveToken(token: String) {
        prefs.edit().putString("jwt_token", token).apply()
    }

    override fun getToken(): String? {
        return prefs.getString("jwt_token", null)
    }

    override fun clearSession() {
        prefs.edit().remove("jwt_token").apply()
    }
}