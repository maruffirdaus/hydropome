package com.motion.hydropome.ui.monitorplants

import com.motion.hydropome.common.model.PlantProgress

data class MonitorPlantsUiState(
    val plantProgresses: List<PlantProgress> = listOf(),
    val searchQuery: String = "",
    val isLoading: Boolean = false
)
