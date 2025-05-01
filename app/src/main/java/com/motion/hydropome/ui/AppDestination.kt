package com.motion.hydropome.ui

import kotlinx.serialization.Serializable

sealed class AppDestination {
    @Serializable
    data object Splash : AppDestination()

    @Serializable
    data object Onboarding : AppDestination()

    @Serializable
    data object Login : AppDestination()

    @Serializable
    data object Register : AppDestination()

    @Serializable
    data object Personalization : AppDestination()

    @Serializable
    data object Qris : AppDestination()

    @Serializable
    data object Main : AppDestination()

    @Serializable
    data object Receipt : AppDestination()

    @Serializable
    data class PlantDetails(
        val plantId: String,
        val isStartPlantEnabled: Boolean = true
    ) : AppDestination()

    @Serializable
    data class PlantProgress(val plantProgressId: String) : AppDestination()
}