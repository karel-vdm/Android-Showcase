package com.karel.authentication.data

import android.content.SharedPreferences

class UserRepository(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val USER_ID = "USER_ID"
        private const val USER_EMAIL = "USER_EMAIL"
    }

    fun saveUserId(userId: String) {
        sharedPreferences.edit().putString(USER_ID, userId).apply()
    }

    fun getUserId(): String {
        return sharedPreferences.getString(USER_ID, "") ?: ""
    }

    fun saveEmail(email: String) {
        sharedPreferences.edit().putString(USER_EMAIL, email).apply()
    }

    fun getEmail(): String {
        return sharedPreferences.getString(USER_EMAIL, "") ?: ""
    }
}