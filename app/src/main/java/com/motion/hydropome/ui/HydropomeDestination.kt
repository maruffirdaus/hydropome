package com.motion.hydropome.ui

import kotlinx.serialization.Serializable

sealed class HydropomeDestination {
    @Serializable
    data object Login : HydropomeDestination()

    @Serializable
    data object Register : HydropomeDestination()

    @Serializable
    data object Home : HydropomeDestination()
}