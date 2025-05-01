package com.motion.hydropome.common.model

import androidx.annotation.DrawableRes

data class Product(
    val title: String,
    @DrawableRes val image: Int,
    val regularPrice: Int,
    val discountedPrice: Int?,
    val category: String
)