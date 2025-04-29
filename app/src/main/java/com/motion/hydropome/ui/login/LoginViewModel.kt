package com.motion.hydropome.ui.login

import android.util.Patterns
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

    fun changeEmail(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                emailErrorMessage = if (email.isBlank()) {
                    "Email tidak boleh kosong."
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    "Format email tidak valid."
                } else {
                    null
                }
            )
        }
        validateForm()
    }

    fun changePassword(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                passwordErrorMessage = if (password.isBlank()) {
                    "Password tidak boleh kosong."
                } else {
                    null
                }
            )
        }
        validateForm()
    }

    private fun validateForm() {
        _uiState.update {
            it.copy(
                isFormValid = it.emailErrorMessage == null &&
                        it.email.isNotBlank() &&
                        it.passwordErrorMessage == null &&
                        it.password.isNotBlank()
            )
        }
    }

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
}