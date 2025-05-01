package com.motion.hydropome.ui.jualBarang

data class SellProductUiState(
    val productName: String = "",
    val category: String = "",
    val price: String = "",
    val contact: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
