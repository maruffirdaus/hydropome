package com.motion.hydropome.ui.sellproduct


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SellProductViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SellProductUiState())
    val uiState: StateFlow<SellProductUiState> = _uiState

    fun onProductNameChange(newName: String) {
        _uiState.value = _uiState.value.copy(productName = newName)
    }

    fun onCategoryChange(newCategory: String) {
        _uiState.value = _uiState.value.copy(category = newCategory)
    }

    fun onPriceChange(newPrice: String) {
        _uiState.value = _uiState.value.copy(price = newPrice)
    }

    fun onContactChange(newContact: String) {
        _uiState.value = _uiState.value.copy(contact = newContact)
    }

    fun onDescriptionChange(newDescription: String) {
        _uiState.value = _uiState.value.copy(description = newDescription)
    }

    fun submitProduct() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, isSuccess = false, errorMessage = null)

            try {
                //firestorenya bangwkwkw
                delay(2000)

                _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }

    fun resetSuccess() {
        _uiState.value = _uiState.value.copy(isSuccess = false)
    }
}
