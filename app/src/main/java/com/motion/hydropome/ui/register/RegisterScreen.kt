package com.motion.hydropome.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.motion.hydropome.ui.theme.HydropomeTheme

@Composable
fun RegisterScreen(
    uiState: RegisterUiState
) {

}

@Preview
@Composable
private fun RegisterScreenPreview() {
    HydropomeTheme {
        RegisterScreen(
            uiState = RegisterUiState()
        )
    }
}