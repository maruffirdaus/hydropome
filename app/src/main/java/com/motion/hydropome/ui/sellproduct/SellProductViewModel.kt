package com.motion.hydropome.ui.sellproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motion.hydropome.common.model.Product
import com.motion.hydropome.common.type.Category
import com.motion.hydropome.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SellProductUiState())
    val uiState = _uiState.asStateFlow()

    fun changeTitle(title: String) {
        _uiState.update {
            it.copy(
                title = title,
                titleErrorMessage = if (title.isBlank()) {
                    "Nama produk tidak boleh kosong."
                } else {
                    null
                }
            )
        }
        validateForm()
    }

    fun changePrice(price: String) {
        _uiState.update {
            it.copy(
                price = price,
                priceErrorMessage = if (price.isBlank()) {
                    "Harga tidak boleh kosong."
                } else if (price.toIntOrNull() == null) {
                    "Harga harus berupa angka."
                } else {
                    null
                }
            )
        }
        validateForm()
    }

    fun changeContact(contact: String) {
        _uiState.update {
            it.copy(
                contact = contact,
                contactErrorMessage = if (contact.isBlank()) {
                    "Kontak penjual tidak boleh kosong."
                } else {
                    null
                }
            )
        }
        validateForm()
    }

    fun changeDescription(description: String) {
        _uiState.update {
            it.copy(
                description = description,
                descriptionErrorMessage = if (description.isBlank()) {
                    "Deskripsi tidak boleh kosong."
                } else {
                    null
                }
            )
        }
        validateForm()
    }

    private fun validateForm() {
        _uiState.update {
            it.copy(
                isFormValid = it.titleErrorMessage == null &&
                        it.title.isNotBlank() &&
                        it.priceErrorMessage == null &&
                        it.price.isNotBlank() &&
                        it.contactErrorMessage == null &&
                        it.contact.isNotBlank() &&
                        it.descriptionErrorMessage == null &&
                        it.description.isNotBlank()
            )
        }
    }

    fun sellProduct(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            productRepository.addProduct(
                Product(
                    title = uiState.value.title,
                    description = uiState.value.description,
                    regularPrice = uiState.value.price.toInt(),
                    category = Category.CUSTOMER_PRODUCT,
                    sellerContact = uiState.value.contact
                )
            ).onSuccess {
                onSuccess()
            }
            _uiState.update {
                it.copy(isLoading = true)
            }
        }
    }
}