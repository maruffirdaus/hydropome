package com.motion.hydropome.ui.editprofile

data class EditProfileUiState(
    val name: String = "",
    val nameErrorMessage: String? = null,
    val email: String = "",
    val emailErrorMessage: String? = null,
    val password: String = "",
    val passwordErrorMessage: String? = null,
    val newPassword: String = "",
    val newPasswordErrorMessage: String? = null,
    val isFormValid: Boolean = false,
    val isLoading: Boolean = false
)
