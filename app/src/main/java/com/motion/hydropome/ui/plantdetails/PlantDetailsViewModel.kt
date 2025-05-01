package com.motion.hydropome.ui.plantdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motion.hydropome.data.repository.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantDetailsViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PlantDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun refreshPlant(plantId: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            plantRepository.getPlant(plantId).onSuccess { plant ->
                _uiState.update {
                    it.copy(plant = plant)
                }
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}