package com.karel.authentication.domain

import android.content.SharedPreferences
import com.karel.authentication.data.UserRepository

class UseCaseSaveUserCredentials(sharedPreferences: SharedPreferences) {

    private val userRepository: UserRepository = UserRepository(sharedPreferences)

    fun saveUserId(userId: String) {
        return userRepository.saveUserId(userId)
    }

    fun saveEmail(email: String) {
        return userRepository.saveUserId(email)
    }

}