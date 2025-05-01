package com.motion.hydropome.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motion.hydropome.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState(isLoading = true))
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        getUserData()
    }

     fun getUserData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val result = userRepository.getUser()
            if (result.isSuccess) {
                val user = result.getOrNull()
                _uiState.update {
                    it.copy(
                        userName = user?.name ?: "Unknown",
                        userEmail = user?.email ?: "No Email",
                        isLoading = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        userName = "Error",
                        userEmail = "Failed to load",
                        isLoading = false
                    )
                }
            }
        }
    }
}
