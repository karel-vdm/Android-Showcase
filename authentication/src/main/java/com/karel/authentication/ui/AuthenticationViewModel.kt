package com.karel.authentication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karel.authentication.data.model.AuthenticationResult
import com.karel.authentication.domain.UseCaseAuthenticateUser
import com.karel.authentication.domain.UseCaseSaveUserCredentials
import com.karel.authentication.domain.UseCaseValidateUserCredentials
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val useCaseValidateUserCredentials: UseCaseValidateUserCredentials,
    private val useCaseAuthenticateUser: UseCaseAuthenticateUser,
    private val useCaseSaveUserCredentials: UseCaseSaveUserCredentials,
) : ViewModel() {

    private var _userNameError = MutableLiveData<String>()
    val userNameError: LiveData<String> get() = _userNameError

    private var _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> get() = _passwordError

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var _isLoginSuccess = MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean> get() = _isLoginSuccess

    fun onLoginClicked(userName: String, password: String) {
        val isUserCredentialsValid = validateUserNameAndPassword(userName, password)
        if (isUserCredentialsValid) {
            login(userName, password)
        }
    }

    fun onSignupClicked(userName: String, password: String) {
        val isUserCredentialsValid = validateUserNameAndPassword(userName, password)
        if (isUserCredentialsValid) {
            signup(userName, password)
        }
    }

    private fun validateUserNameAndPassword(userName: String, password: String): Boolean {
        val validationResult =
            useCaseValidateUserCredentials.validateUserCredentials(userName, password)

        if (!validationResult.isValid) {
            if (!validationResult.isUserNameValid) {
                _userNameError.postValue(validationResult.userNameErrorMessage)
            }

            if (!validationResult.isPasswordValid) {
                _passwordError.postValue(validationResult.passwordErrorMessage)
            }
        }

        return validationResult.isValid
    }

    private fun login(userName: String, password: String) {
        viewModelScope.launch {
            useCaseAuthenticateUser.login(userName, password)
                .onStart {
                    onLoading()
                }
                .catch {
                    onError(it.message ?: "Error logging in, please try again")
                }
                .collect { authResult ->
                    onCompleted(authResult)
                }
        }
    }

    private fun signup(userName: String, password: String) {
        viewModelScope.launch {
            useCaseAuthenticateUser.signup(userName, password)
                .onStart {
                    onLoading()
                }
                .catch {
                    onError(it.message ?: "Error creating account, please try again")
                }
                .collect { authResult ->
                    onCompleted(authResult)
                }
        }
    }

    private fun onLoading() {
        _isLoading.postValue(true)
    }

    private fun onError(error: String) {
        _isLoading.postValue(false)
        _error.postValue(error)
    }

    private fun onCompleted(authResult: AuthenticationResult) {
        if (authResult.userId.isEmpty()) {
            onError("Invalid user, please try again")
        } else {
            useCaseSaveUserCredentials.saveUserId(authResult.userId)
            _isLoading.postValue(false)
            _isLoginSuccess.postValue(true)
        }
    }

}