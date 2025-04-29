package com.motion.hydropome.ui.register

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
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun changeName(name: String) {
        _uiState.update {
            it.copy(
                name = name,
                nameErrorMessage = if (name.isBlank()) {
                    "Nama tidak boleh kosong."
                } else {
                    null
                }
            )
        }
        validateForm()
    }

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
                } else if (password.length < 8) {
                    "Password minimal 8 karakter."
                } else {
                    null
                }
            )
        }
        validateForm()
    }

    fun changeConfirmPassword(confirmPassword: String) {
        _uiState.update {
            it.copy(
                confirmPassword = confirmPassword,
                confirmPasswordErrorMessage = if (confirmPassword.isBlank()) {
                    "Konfirmasi password tidak boleh kosong."
                } else if (confirmPassword != uiState.value.password) {
                    "Konfirmasi password tidak sesuai."
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
                isFormValid = it.nameErrorMessage == null &&
                        it.name.isNotBlank() &&
                        it.emailErrorMessage == null &&
                        it.email.isNotBlank() &&
                        it.passwordErrorMessage == null &&
                        it.password.isNotBlank() &&
                        it.confirmPasswordErrorMessage == null &&
                        it.confirmPassword.isNotBlank()
            )
        }
    }

    fun register(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            authRepository.register(
                name = uiState.value.name,
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