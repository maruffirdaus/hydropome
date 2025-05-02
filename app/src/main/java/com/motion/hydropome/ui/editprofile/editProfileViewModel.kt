package com.motion.hydropome.ui.editprofile


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
class EditProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository // adjust this if needed
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState = _uiState.asStateFlow()

    fun changeName(name: String) {
        _uiState.update {
            it.copy(
                name = name,
                nameErrorMessage = if (name.isBlank()) "Nama tidak boleh kosong." else null
            )
        }
        validateForm()
    }

    fun changeEmail(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                emailErrorMessage = when {
                    email.isBlank() -> "Email tidak boleh kosong."
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Format email tidak valid."
                    else -> null
                }
            )
        }
        validateForm()
    }

    fun changePassword(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                passwordErrorMessage = if (password.isBlank()) "Password tidak boleh kosong."
                else if (password.length < 8) "Password minimal 8 karakter."
                else null
            )
        }
        validateForm()
    }

    fun changeNewPassword(newPassword: String) {
        _uiState.update {
            it.copy(
                newPassword = newPassword,
                newPasswordErrorMessage = if (newPassword.isBlank()) "Password baru tidak boleh kosong."
                else if (newPassword.length < 8) "Password baru minimal 8 karakter."
                else null
            )
        }
        validateForm()
    }

    private fun validateForm() {
        _uiState.update {
            it.copy(
                isFormValid = it.nameErrorMessage == null && it.name.isNotBlank()
                        && it.emailErrorMessage == null && it.email.isNotBlank()
                        && it.passwordErrorMessage == null && it.password.isNotBlank()
                        && it.newPasswordErrorMessage == null && it.newPassword.isNotBlank()
            )
        }
    }

    fun saveProfileChanges(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            authRepository.updateProfile(
                name = uiState.value.name,
                email = uiState.value.email,
                password = uiState.value.password,
                newPassword = uiState.value.newPassword
            ).onSuccess {
                onSuccess()
            }.onFailure {
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }
}
