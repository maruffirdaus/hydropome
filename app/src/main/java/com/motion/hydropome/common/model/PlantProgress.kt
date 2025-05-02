package com.motion.hydropome.common.model

data class PlantProgress(
    val id: String = "",
    val plant: Plant = Plant(),
    val day: Int = 0,
    val taskStates: List<Boolean> = listOf()
) {
    fun toFirestore(): Map<String, Any> = mapOf(
        "id" to id,
        "plantId" to plant.id,
        "day" to day,
        "taskStates" to taskStates
    )

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun fromFirestore(data: Map<String, Any>, plant: Plant): PlantProgress = PlantProgress(
            id = data["id"] as? String ?: "",
            plant = plant,
            day = (data["day"] as? Long ?: 0).toInt(),
            taskStates = data["taskStates"] as? List<Boolean> ?: emptyList()
        )
    }
}
