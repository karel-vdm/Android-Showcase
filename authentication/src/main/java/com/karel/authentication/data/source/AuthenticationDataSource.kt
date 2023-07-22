package com.karel.authentication.data.source

import com.karel.authentication.data.model.AuthenticationResult
import kotlinx.coroutines.flow.Flow

interface AuthenticationDataSource {
    fun login(email: String, password: String): Flow<AuthenticationResult>

    fun signup(email: String, password: String): Flow<AuthenticationResult>
}