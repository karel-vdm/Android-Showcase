package com.karel.authentication.data.source.firebase.transform

import com.google.firebase.auth.AuthResult
import com.karel.authentication.data.model.AuthenticationResult

object AuthResultTransformer {
    fun AuthResult.transform() = AuthenticationResult(
        userId = user?.uid ?: "",
        email = user?.email ?: "",
    )
}