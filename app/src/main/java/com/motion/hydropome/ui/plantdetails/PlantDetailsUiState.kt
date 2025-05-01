package com.motion.hydropome.ui.plantdetails

import com.motion.hydropome.common.model.Plant

data class PlantDetailsUiState(
    val plant: Plant? = null,
    val isLoading: Boolean = false
)
