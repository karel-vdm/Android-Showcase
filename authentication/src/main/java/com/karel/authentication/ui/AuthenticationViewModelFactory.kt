package com.karel.authentication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karel.authentication.domain.UseCaseAuthenticateUser
import com.karel.authentication.domain.UseCaseSaveUserCredentials
import com.karel.authentication.domain.UseCaseValidateUserCredentials


class AuthenticationViewModelFactory(
    private val useCaseValidateUserCredentials: UseCaseValidateUserCredentials,
    private val useCaseAuthenticateUser: UseCaseAuthenticateUser,
    private val useCaseSaveUserCredentials: UseCaseSaveUserCredentials,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            UseCaseValidateUserCredentials::class.java,
            UseCaseAuthenticateUser::class.java,
            UseCaseSaveUserCredentials::class.java,
        ).newInstance(
            useCaseValidateUserCredentials,
            useCaseAuthenticateUser,
            useCaseSaveUserCredentials,
        )
    }

}