package com.motion.hydropome.ui.profile

import com.motion.hydropome.common.model.User

data class ProfileUiState(
    val user: User? = null,
    val isLoading: Boolean = false
)
