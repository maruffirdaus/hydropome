package com.motion.hydropome.common.model

import com.motion.hydropome.common.type.Category

data class Product(
    val id: String = "",
    val title: String = "",
    val image: String = "",
    val description: String = "",
    val regularPrice: Int = 0,
    val discountedPrice: Int? = null,
    val category: Category = Category.CUSTOMER_PRODUCT,
    val sellerContact: String? = null,
) {
    fun toFirestore(): Map<String, Any?> = mapOf(
        "id" to id,
        "title" to title,
        "image" to image,
        "description" to description,
        "regularPrice" to regularPrice,
        "discountedPrice" to discountedPrice,
        "category" to category.ordinal,
        "sellerContact" to sellerContact
    )

    companion object {
        fun fromFirestore(data: Map<String, Any?>): Product = Product(
            id = data["id"] as? String ?: "",
            title = data["title"] as? String ?: "",
            image = data["image"] as? String ?: "",
            description = data["description"] as? String ?: "",
            regularPrice = (data["regularPrice"] as? Long ?: 0).toInt(),
            discountedPrice = (data["discountedPrice"] as? Long)?.toInt(),
            category = Category.entries[(data["category"] as? Long ?: 0).toInt()],
            sellerContact = data["sellerContact"] as? String
        )
    }
}