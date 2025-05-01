package com.motion.hydropome.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.motion.hydropome.common.model.Plant
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PlantRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun getPlants(): Result<List<Plant>> {
        return try {
            val plantsSnapshot = firestore.collection("plants")
                .get()
                .await()
            val plants = plantsSnapshot.documents.mapNotNull { document ->
                document.data?.let {
                    Plant.fromFirestore(it)
                }
            }
            Result.success(plants)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPlant(id: String): Result<Plant> {
        try {
            val plantSnapshot = firestore.collection("plants")
                .document(id)
                .get()
                .await()
            plantSnapshot.data?.let {
                val plant = Plant.fromFirestore(it)
                return Result.success(plant)
            }
            throw NoSuchElementException("Plant not found")
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}