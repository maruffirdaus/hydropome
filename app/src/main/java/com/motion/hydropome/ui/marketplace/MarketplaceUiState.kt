package com.motion.hydropome.ui.marketplace

import com.motion.hydropome.R
import com.motion.hydropome.common.model.Product
import com.motion.hydropome.common.type.Category

data class MarketplaceUiState(
    val products: List<Product> = listOf(
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
    val searchQuery: String = "",
    val selectedCategory: Category? = null,
    val isLoading: Boolean = false
)
