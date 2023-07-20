package com.karel.authentication.domain.model

data class ValidateUserCredentialsResult(
    val userNameValidationResult: ValidateUserNameResult = ValidateUserNameResult(),
    val passwordValidationResult: ValidatePasswordResult = ValidatePasswordResult(),
) {
    val isValid: Boolean
        get() {
            return userNameValidationResult.isUserNameValid &&
                    passwordValidationResult.isPasswordValid
        }

    val isUserNameValid: Boolean
        get() {
            return userNameValidationResult.isUserNameValid
        }

    val isPasswordValid: Boolean
        get() {
            return passwordValidationResult.isPasswordValid
        }

    val userNameErrorMessage: String
        get() {
            return userNameValidationResult.userNameErrorMessage
        }

    val passwordErrorMessage: String
        get() {
            return passwordValidationResult.passwordErrorMessage
        }
}