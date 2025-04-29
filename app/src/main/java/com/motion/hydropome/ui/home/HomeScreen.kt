package com.motion.hydropome.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.motion.hydropome.ui.theme.HydropomeTheme

@Composable
fun HomeScreen(
    uiState: HomeUiState
) {

}

@Preview
@Composable
private fun HomeScreenPreview() {
    HydropomeTheme {
        HomeScreen(
            uiState = HomeUiState()
        )
    }
}