package com.motion.hydropome.ui.home

import com.motion.hydropome.R
import com.motion.hydropome.common.model.Plant
import com.motion.hydropome.common.model.Product
import com.motion.hydropome.common.model.User
import com.motion.hydropome.common.type.Category

data class HomeUiState(
    val user: User? = null,
    val searchQuery: String = "",
    val plants: List<Plant> = listOf(),
    val flashSaleProducts: List<Product> = listOf(
        Product(
            title = "Basic Starter Kit",
            image = R.drawable.img_product_0,
            regularPrice = 50000,
            discountedPrice = 25000,
            category = Category.STARTER_KIT
        ),
        Product(
            title = "Basic Starter Kit",
            image = R.drawable.img_product_1,
            regularPrice = 50000,
            discountedPrice = 25000,
            category = Category.STARTER_KIT
        ),
        Product(
            title = "Basic Starter Kit",
            image = R.drawable.img_product_0,
            regularPrice = 50000,
            discountedPrice = 25000,
            category = Category.STARTER_KIT
        )
    ),
    val isLoading: Boolean = false
)
