package com.motion.hydropome.ui.marketplace

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MarketplaceViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MarketplaceUiState())
    val uiState = _uiState.asStateFlow()
}