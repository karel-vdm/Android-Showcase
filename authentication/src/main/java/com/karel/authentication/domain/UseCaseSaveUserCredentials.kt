package com.karel.authentication.domain

import com.karel.authentication.data.UserRepository

class UseCaseSaveUserCredentials(private val userRepository: UserRepository) {

    fun saveUserId(userId: String) {
        return userRepository.saveUserId(userId)
    }

    fun saveEmail(email: String) {
        return userRepository.saveUserId(email)
    }

}