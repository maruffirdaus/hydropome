package com.motion.hydropome.ui.monitorplants

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MonitorPlantsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MonitorPlantsUiState())
    val uiState = _uiState.asStateFlow()
}