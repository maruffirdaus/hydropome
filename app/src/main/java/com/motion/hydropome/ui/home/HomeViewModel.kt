package com.motion.hydropome.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motion.hydropome.data.repository.PlantRepository
import com.motion.hydropome.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val plantRepository: PlantRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun refreshUser() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            userRepository.getUser().onSuccess { user ->
                _uiState.update { it.copy(user = user) }
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun search(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
    }

    fun refreshPlants() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            plantRepository.getPlants().onSuccess { plants ->
                _uiState.update {
                    it.copy(plants = plants)
                }
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}