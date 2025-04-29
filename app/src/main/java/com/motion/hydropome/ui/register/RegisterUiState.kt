package com.motion.hydropome.ui.register

data class RegisterUiState(
    val name: String = "",
    val nameErrorMessage: String? = null,
    val email: String = "",
    val emailErrorMessage: String? = null,
    val password: String = "",
    val passwordErrorMessage: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordErrorMessage: String? = null,
    val isFormValid: Boolean = false,
    val isLoading: Boolean = false
)
