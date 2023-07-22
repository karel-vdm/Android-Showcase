package com.karel.authentication.data

import com.karel.authentication.data.model.AuthenticationResult
import com.karel.authentication.data.source.AuthenticationDataSource
import kotlinx.coroutines.flow.Flow

class AuthenticationRepository(private val dataSource: AuthenticationDataSource) {
    fun login(email: String, password: String): Flow<AuthenticationResult> {
        return dataSource.login(email, password)
    }

    fun signup(email: String, password: String): Flow<AuthenticationResult> {
        return dataSource.signup(email, password)
    }
}