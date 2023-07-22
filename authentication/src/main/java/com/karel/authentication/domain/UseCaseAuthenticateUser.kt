package com.karel.authentication.domain

import com.karel.authentication.data.AuthenticationRepository
import com.karel.authentication.data.model.AuthenticationResult
import kotlinx.coroutines.flow.Flow

class UseCaseAuthenticateUser(private val authenticationRepository: AuthenticationRepository) {

    fun login(email: String, password: String): Flow<AuthenticationResult> {
        return authenticationRepository.login(email, password)
    }

    fun signup(email: String, password: String): Flow<AuthenticationResult> {
        return authenticationRepository.signup(email, password)
    }
}