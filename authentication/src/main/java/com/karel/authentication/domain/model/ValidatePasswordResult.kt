package com.karel.authentication.domain.model

data class ValidatePasswordResult(
    val isPasswordValid: Boolean = false,
    val passwordErrorMessage: String = "",
)