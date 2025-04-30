package com.motion.hydropome.ui.qris

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrisViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(QRISUiState())
    val uiState = _uiState.asStateFlow()

    // Start countdown timer
    fun startCountdown() {
        viewModelScope.launch {
            var timeInSeconds = convertTimeToSeconds(_uiState.value.timer)
            while (timeInSeconds > 0) {
                delay(1000)
                timeInSeconds--
                _uiState.update {
                    it.copy(timer = convertSecondsToTime(timeInSeconds))
                }
            }
        }
    }

    fun setLoadingState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun convertTimeToSeconds(time: String): Int {
        val parts = time.split(":")
        val hours = parts[0].toInt()
        val minutes = parts[1].toInt()
        val seconds = parts[2].toInt()
        return hours * 3600 + minutes * 60 + seconds
    }

    private fun convertSecondsToTime(seconds: Int): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
    }
}
