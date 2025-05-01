package com.motion.hydropome.ui.plantprogress

import com.motion.hydropome.common.model.PlantProgress


data class PlantProgressUiState(
    val plantProgress: PlantProgress? = null,
    val isLoading: Boolean = false
)
