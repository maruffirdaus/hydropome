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
            val querySnapshot = firestore.collection("plants")
                .get()
                .await()
            val plants = querySnapshot.documents.mapNotNull { document ->
                document.data?.let {
                    Plant.fromFirestore(it)
                }
            }
            Result.success(plants)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}