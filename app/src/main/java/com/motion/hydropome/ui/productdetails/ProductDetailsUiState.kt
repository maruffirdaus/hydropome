package com.motion.hydropome.ui.productdetails

import com.motion.hydropome.common.model.Product

data class ProductDetailsUiState(
    val product: Product? = null,
    val isLoading: Boolean = false
)
