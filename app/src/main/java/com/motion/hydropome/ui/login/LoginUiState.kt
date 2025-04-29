package com.motion.hydropome.ui.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val emailErrorMessage: String? = null,
    val password: String = "",
    val passwordErrorMessage: String? = null,
)
