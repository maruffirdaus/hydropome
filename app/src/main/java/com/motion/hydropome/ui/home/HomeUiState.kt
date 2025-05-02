package com.motion.hydropome.ui.home

import com.motion.hydropome.common.model.Plant
import com.motion.hydropome.common.model.Product
import com.motion.hydropome.common.model.User

data class HomeUiState(
    val user: User? = null,
    val searchQuery: String = "",
    val plants: List<Plant> = listOf(),
    val flashSaleProducts: List<Product> = listOf(),
    val isLoading: Boolean = false
)
