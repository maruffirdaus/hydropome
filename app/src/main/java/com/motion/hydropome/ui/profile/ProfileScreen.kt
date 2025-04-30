package com.motion.hydropome.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
) {

}

@Preview
@Composable
fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen(
            uiState = ProfileUiState()
        )
    }
}