package com.motion.hydropome.common.model

import androidx.annotation.DrawableRes
import com.motion.hydropome.common.type.Category

data class Product(
    val title: String = "",
    @DrawableRes val image: Int = 0,
    val description: String = "",
    val regularPrice: Int = 0,
    val discountedPrice: Int? = null,
    val category: Category = Category.CUSTOMER_PRODUCT,
    val sellerContact: String? = null,
)