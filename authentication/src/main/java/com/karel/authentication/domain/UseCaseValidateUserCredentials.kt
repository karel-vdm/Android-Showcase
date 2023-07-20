package com.karel.authentication.domain

import com.karel.authentication.domain.model.ValidatePasswordResult
import com.karel.authentication.domain.model.ValidateUserCredentialsResult
import com.karel.authentication.domain.model.ValidateUserNameResult

class UseCaseValidateUserCredentials {

    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

    fun validateUserCredentials(userName: String, password: String): ValidateUserCredentialsResult {
        return ValidateUserCredentialsResult(
            userNameValidationResult = validateUserName(userName),
            passwordValidationResult = validatePassword(password),
        )
    }

    private fun validateUserName(userName: String): ValidateUserNameResult {
        val isUserNameValid = userName.matches(emailRegex.toRegex())
        return ValidateUserNameResult(
            isUserNameValid = isUserNameValid,
            userNameErrorMessage = "Please enter a valid email address",
        )
    }

    private fun validatePassword(password: String): ValidatePasswordResult {
        val isPasswordValid = password.length > 5
        return ValidatePasswordResult(
            isPasswordValid = isPasswordValid,
            passwordErrorMessage = "Please enter a password that is at least 6 characters or longer",
        )
    }

}