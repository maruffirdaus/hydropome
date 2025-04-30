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
    data object Main : AppDestination()
}