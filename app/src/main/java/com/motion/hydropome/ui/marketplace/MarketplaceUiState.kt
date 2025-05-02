package com.motion.hydropome.ui.marketplace

import com.motion.hydropome.R
import com.motion.hydropome.common.model.Product
import com.motion.hydropome.common.type.Category

data class MarketplaceUiState(
    val products: List<Product> = listOf(),
    val searchQuery: String = "",
    val selectedCategory: Category? = null,
    val isLoading: Boolean = false
)
