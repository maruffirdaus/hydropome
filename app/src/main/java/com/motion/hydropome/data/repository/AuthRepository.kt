package com.motion.hydropome.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.motion.hydropome.common.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(name: String, email: String, password: String): Result<Unit> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            authResult.user?.let {
                val user = User(
                    name = name,
                    email = email
                )
                firestore.collection("users")
                    .document(it.uid)
                    .set(user)
                    .await()
            }
            auth.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}