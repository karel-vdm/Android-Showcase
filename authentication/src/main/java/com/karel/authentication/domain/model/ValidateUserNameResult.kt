package com.karel.authentication.domain.model

data class ValidateUserNameResult(
    val isUserNameValid: Boolean = false,
    val userNameErrorMessage: String = "",
)