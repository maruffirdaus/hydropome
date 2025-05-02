package com.motion.hydropome.ui.plantprogress

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
class PlantProgressViewModel @Inject constructor(
    private val plantProgressRepository: PlantProgressRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PlantProgressUiState())
    val uiState = _uiState.asStateFlow()

    fun refreshPlantProgress(id: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            plantProgressRepository.getPlantProgress(id).onSuccess { plantProgress ->
                _uiState.update {
                    it.copy(plantProgress = plantProgress)
                }
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun changeTaskCompletion(index: Int) {
        uiState.value.plantProgress?.let { plantProgress ->
            viewModelScope.launch {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                plantProgressRepository.updatePlantProgress(
                    plantProgress.copy(
                        taskStates = plantProgress.taskStates.let {
                            val copy = it.toMutableList()
                            copy[index] = !copy[index]
                            copy
                        }
                    )
                ).onSuccess {
                    refreshPlantProgress(plantProgress.id)
                }
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    fun completeDay() {
        uiState.value.plantProgress?.let { plantProgress ->
            viewModelScope.launch {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                plantProgressRepository.advancePlantProgress(plantProgress).onSuccess {
                    refreshPlantProgress(plantProgress.id)
                }
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    fun completeProgress(onSuccess: () -> Unit) {
        uiState.value.plantProgress?.let { plantProgress ->
            viewModelScope.launch {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                plantProgressRepository.deletePlantProgress(plantProgress.id).onSuccess {
                    onSuccess()
                }
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }
}