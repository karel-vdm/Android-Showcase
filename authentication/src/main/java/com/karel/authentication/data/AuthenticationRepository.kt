package com.karel.authentication.data

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class AuthenticationRepository {

    private val firebaseAuth: FirebaseAuth = Firebase.auth

    fun login(email: String, password: String): Flow<AuthResult> = flow {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        emit(result)
    }.flowOn(Dispatchers.IO)

    fun signup(email: String, password: String): Flow<AuthResult> = flow {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        emit(result)
    }.flowOn(Dispatchers.IO)

}