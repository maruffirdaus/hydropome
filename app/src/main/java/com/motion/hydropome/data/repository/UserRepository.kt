package com.motion.hydropome.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.motion.hydropome.common.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun getUser(): Result<User?> {
        try {
            val user = auth.currentUser
            if (user != null) {
                val documentSnapshot = firestore.collection("users")
                    .document(user.uid)
                    .get()
                    .await()
                if (documentSnapshot.exists()) {
                    return Result.success(documentSnapshot.toObject(User::class.java))
                }
            }
            return Result.success(null)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun savePreferences(preferences: Map<String, List<Int>>): Result<Unit> {
        return try {
            val user = auth.currentUser
            if (user != null) {
                firestore.collection("users")
                    .document(user.uid)
                    .set(mapOf("preferences" to preferences), SetOptions.merge())
                    .await()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}