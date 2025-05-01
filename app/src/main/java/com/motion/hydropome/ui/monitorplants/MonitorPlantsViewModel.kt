package com.motion.hydropome.ui.monitorplants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motion.hydropome.data.repository.PlantProgressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonitorPlantsViewModel @Inject constructor(
    private val plantProgressRepository: PlantProgressRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MonitorPlantsUiState())
    val uiState = _uiState.asStateFlow()

    fun refreshPlantProgresses() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            plantProgressRepository.getPlantProgresses().onSuccess { plantProgresses ->
                _uiState.update {
                    it.copy(plantProgresses = plantProgresses)
                }
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun changeSearchQuery(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
    }
}