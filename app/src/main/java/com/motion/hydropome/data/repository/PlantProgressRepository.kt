package com.motion.hydropome.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.motion.hydropome.common.model.Plant
import com.motion.hydropome.common.model.PlantProgress
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PlantProgressRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun getPlantProgresses(): Result<List<PlantProgress>> {
        return try {
            val userId = auth.currentUser?.uid ?: throw IllegalStateException("User not logged in")
            val plantProgressesSnapshot = firestore.collection("users")
                .document(userId)
                .collection("plantProgresses")
                .get()
                .await()
            val plantProgresses = plantProgressesSnapshot.documents.mapNotNull { document ->
                document.data?.let { plantProgress ->
                    val plantSnapshot = firestore.collection("plants")
                        .document(plantProgress["plantId"] as String)
                        .get()
                        .await()
                    plantSnapshot.data?.let { plant ->
                        PlantProgress.fromFirestore(plantProgress, Plant.fromFirestore(plant))
                    }
                }
            }
            Result.success(plantProgresses)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPlantProgress(id: String): Result<PlantProgress> {
        try {
            val userId = auth.currentUser?.uid ?: throw IllegalStateException("User not logged in")
            val plantProgressSnapshot = firestore.collection("users")
                .document(userId)
                .collection("plantProgresses")
                .document(id)
                .get()
                .await()
            plantProgressSnapshot.data?.let { data ->
                val plantSnapshot = firestore.collection("plants")
                    .document(id)
                    .get()
                    .await()
                plantSnapshot.data?.let { plant ->
                    val plantProgress = PlantProgress.fromFirestore(data, Plant.fromFirestore(plant))
                    return Result.success(plantProgress)
                }
                throw NoSuchElementException("Plant not found")
            }
            throw NoSuchElementException("Plant progress not found")
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun addPlantProgress(plantProgress: PlantProgress): Result<Unit> {
        return try {
            val userId = auth.currentUser?.uid ?: throw IllegalStateException("User not logged in")
            val emptyDocument = firestore.collection("users")
                .document(userId)
                .collection("plantProgresses")
                .add(emptyMap<String, Any>())
                .await()

            firestore.collection("users")
                .document(userId)
                .collection("plantProgresses")
                .document(emptyDocument.id)
                .set(plantProgress.copy(id = emptyDocument.id).toFirestore())
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updatePlantProgress(plantProgress: PlantProgress): Result<Unit> {
        return try {
            val userId = auth.currentUser?.uid ?: throw IllegalStateException("User not logged in")
            firestore.collection("users")
                .document(userId)
                .collection("plantProgresses")
                .document(plantProgress.id)
                .set(plantProgress.toFirestore())
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}