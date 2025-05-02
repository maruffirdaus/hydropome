package com.motion.hydropome.ui.marketplace

import androidx.lifecycle.ViewModel
import com.motion.hydropome.common.type.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MarketplaceViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MarketplaceUiState())
    val uiState = _uiState.asStateFlow()

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