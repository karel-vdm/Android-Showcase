package com.karel.authentication.domain

import com.google.firebase.auth.AuthResult
import com.karel.authentication.data.AuthenticationRepository
import kotlinx.coroutines.flow.Flow

class UseCaseAuthenticateUser {

    private val authenticationRepository: AuthenticationRepository = AuthenticationRepository()

    fun login(email: String, password: String): Flow<AuthResult> {
        return authenticationRepository.login(email, password)
    }

    fun signup(email: String, password: String): Flow<AuthResult> {
        return authenticationRepository.signup(email, password)
    }

}