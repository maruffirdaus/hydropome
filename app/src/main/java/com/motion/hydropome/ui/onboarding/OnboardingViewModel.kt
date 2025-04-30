package com.motion.hydropome.ui.onboarding

import androidx.lifecycle.ViewModel
import com.motion.hydropome.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState = _uiState.asStateFlow()

    fun incrementPage() {
        _uiState.update {
            it.copy(page = it.page + 1)
        }
    }

    fun decrementPage() {
        _uiState.update {
            it.copy(page = it.page - 1)
        }
    }
}