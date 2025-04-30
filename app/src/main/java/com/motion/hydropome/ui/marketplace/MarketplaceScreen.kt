package com.motion.hydropome.ui.marketplace

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun MarketplaceScreen(
    uiState: MarketplaceUiState
) {

}

@Preview
@Composable
fun MarketplaceScreenPreview() {
    AppTheme {
        MarketplaceScreen(
            uiState = MarketplaceUiState()
        )
    }
}