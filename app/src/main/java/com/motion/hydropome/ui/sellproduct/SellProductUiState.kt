package com.motion.hydropome.ui.sellproduct

data class SellProductUiState(
    val title: String = "",
    val titleErrorMessage: String? = null,
    val price: String = "",
    val priceErrorMessage: String? = null,
    val contact: String = "",
    val contactErrorMessage: String? = null,
    val description: String = "",
    val descriptionErrorMessage: String? = null,
    val isFormValid: Boolean = false,
    val isLoading: Boolean = false
)
