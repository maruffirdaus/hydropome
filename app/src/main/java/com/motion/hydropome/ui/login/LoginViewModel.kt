package com.motion.hydropome.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motion.hydropome.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            authRepository.login(
                email = uiState.value.email,
                password = uiState.value.password
            ).onSuccess {
                onSuccess()
            }.onFailure {
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
    fun changeEmail(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                emailErrorMessage = if (email.isBlank()) {
                    "Nama tidak boleh kosong."
                } else {
                    null
                }
            )
        }
    }
    fun passwordChange(pass: String) {
        _uiState.update {
            it.copy(
                password = pass,
                passwordErrorMessage =   if (pass.isBlank()) {
                    "Password tidak boleh kosong."
                } else {
                    null
                }
            )
        }
    }
}