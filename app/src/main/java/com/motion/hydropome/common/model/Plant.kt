package com.motion.hydropome.common.model

import com.motion.hydropome.common.type.Difficulty

data class Plant(
    val id: String = "",
    val image: String = "",
    val title: String = "",
    val difficulty: Difficulty = Difficulty.EASY,
    val duration: String = "",
    val description: String = "",
    val toolsAndMaterials: List<ToolMaterial> = listOf(),
    val videoTutorial: String = "",
    val tasksByDay: List<DailyTasks> = listOf(),
) {
    data class ToolMaterial(
        val title: String = "",
        val description: String? = null
    )

    data class DailyTasks(
        val tasks: List<String> = listOf(),
        val tip: String = ""
    )

    fun toFirestore(): Map<String, Any> = mapOf(
        "id" to id,
        "image" to image,
        "title" to title,
        "difficulty" to difficulty.ordinal,
        "duration" to duration,
        "description" to description,
        "toolsAndMaterials" to toolsAndMaterials.map {
            mapOf(
                "title" to it.title,
                "description" to it.description
            )
        },
        "videoTutorial" to videoTutorial,
        "tasksByDay" to tasksByDay.map {
            mapOf(
                "tasks" to it.tasks,
                "tip" to it.tip
            )
        }
    )

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun fromFirestore(data: Map<String, Any>): Plant = Plant(
            id = data["id"] as? String ?: "",
            image = data["image"] as? String ?: "",
            title = data["title"] as? String ?: "",
            difficulty = Difficulty.entries[(data["difficulty"] as? Long ?: 0).toInt()],
            duration = data["duration"] as? String ?: "",
            description = data["description"] as? String ?: "",
            toolsAndMaterials = (data["toolsAndMaterials"] as? List<Map<String, String>>
                ?: listOf()).let {
                it.map { toolMaterial ->
                    ToolMaterial(
                        title = toolMaterial["title"] ?: "",
                        description = toolMaterial["description"]
                    )
                }
            },
            videoTutorial = data["videoTutorial"] as? String ?: "",
            tasksByDay = (data["tasksByDay"] as? List<Map<String, Any>> ?: listOf()).let {
                it.map { dailyTasks ->
                    DailyTasks(
                        tasks = dailyTasks["tasks"] as? List<String> ?: emptyList(),
                        tip = dailyTasks["tip"] as? String ?: ""
                    )
                }
            }
        )
    }
}
