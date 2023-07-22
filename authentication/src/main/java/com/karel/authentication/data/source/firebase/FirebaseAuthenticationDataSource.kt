package com.karel.authentication.data.source.firebase

import com.google.firebase.auth.FirebaseAuth
import com.karel.authentication.data.model.AuthenticationResult
import com.karel.authentication.data.source.AuthenticationDataSource
import com.karel.authentication.data.source.firebase.transform.AuthResultTransformer.transform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class FirebaseAuthenticationDataSource(private val firebaseAuth: FirebaseAuth) : AuthenticationDataSource {

    override fun login(email: String, password: String): Flow<AuthenticationResult> = flow {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        emit(result.transform())
    }.flowOn(Dispatchers.IO)

    override fun signup(email: String, password: String): Flow<AuthenticationResult> = flow {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        emit(result.transform())
    }.flowOn(Dispatchers.IO)

}