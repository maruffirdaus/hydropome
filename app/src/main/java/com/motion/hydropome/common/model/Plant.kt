package com.motion.hydropome.common.model

import com.motion.hydropome.common.type.Difficulty

data class Plant(
    val id: String = "",
    val image: String = "",
    val title: String = "",
    val difficulty: Difficulty = Difficulty.EASY,
    val duration: String = "",
    val description: String = "",
    val toolsAndMaterials: List<Map<String, String>> = listOf(),
    val tutorialVideo: String = ""
) {
    fun toFirestore(): Map<String, Any> = mapOf(
        "id" to id,
        "image" to image,
        "title" to title,
        "difficulty" to difficulty.ordinal,
        "duration" to duration,
        "description" to description,
        "toolsAndMaterials" to toolsAndMaterials,
        "tutorialVideo" to tutorialVideo
    )

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun fromFirestore(data: Map<String, Any>): Plant = Plant(
            id = data["id"] as String,
            image = data["image"] as String,
            title = data["title"] as String,
            difficulty = Difficulty.entries[(data["difficulty"] as Long).toInt()],
            duration = data["duration"] as String,
            description = data["description"] as String,
            toolsAndMaterials = data["toolsAndMaterials"] as List<Map<String, String>>,
            tutorialVideo = data["tutorialVideo"] as String
        )
    }
}
