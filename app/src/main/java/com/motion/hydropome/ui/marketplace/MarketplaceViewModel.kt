package com.motion.hydropome.ui.marketplace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motion.hydropome.common.type.Category
import com.motion.hydropome.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketplaceViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MarketplaceUiState())
    val uiState = _uiState.asStateFlow()

    fun refreshProducts() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            productRepository.getProducts().onSuccess { products ->
                _uiState.update {
                    it.copy(products = products)
                }
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun search(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
    }

    fun changeSelectedCategory(category: Category) {
        _uiState.update {
            it.copy(selectedCategory = if (it.selectedCategory == category) null else category)
        }
    }
}