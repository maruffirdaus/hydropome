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
                val user = hashMapOf(
                    "name" to name,
                    "email" to email
                )
                firestore.collection("users")
                    .document(it.uid)
                    .set(user)
                    .await()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUser(): Result<User?> {
        return try {
            val user = auth.currentUser
            if (user != null) {
                val documentSnapshot = firestore.collection("users")
                    .document(user.uid)
                    .get()
                    .await()
                if (documentSnapshot.exists()) {
                    Result.success(
                        User(
                            name = documentSnapshot.getString("name") ?: "",
                            email = documentSnapshot.getString("email") ?: ""
                        )
                    )
                }
            }
            Result.success(null)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}