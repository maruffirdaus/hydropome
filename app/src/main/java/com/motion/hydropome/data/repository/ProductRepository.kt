package com.motion.hydropome.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.motion.hydropome.common.model.Product
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun getProducts(): Result<List<Product>> {
        try {
            val productsSnapshot = firestore.collection("products")
                .get()
                .await()
            val products = productsSnapshot.documents.mapNotNull { document ->
                document.data?.let { product ->
                    Product.fromFirestore(product)
                }
            }
            return Result.success(products)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun getProduct(id: String): Result<Product> {
        try {
            val productSnapshot = firestore.collection("products")
                .document(id)
                .get()
                .await()
            productSnapshot.data?.let { product ->
                return Result.success(Product.fromFirestore(product))
            }
            throw NoSuchElementException("Product not found")
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun addProduct(product: Product): Result<Unit> {
        return try {
            val emptyDocument = firestore.collection("products")
                .add(emptyMap<String, Any>())
                .await()

            firestore.collection("products")
                .document(emptyDocument.id)
                .set(product.copy(id = emptyDocument.id).toFirestore())
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}