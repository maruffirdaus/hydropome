package com.motion.hydropome.ui.productdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motion.hydropome.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun refreshProduct(id: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            productRepository.getProduct(id).onSuccess { product ->
                _uiState.update {
                    it.copy(product = product)
                }
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}