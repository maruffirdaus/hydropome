package com.motion.hydropome.ui.monitorplants

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun MonitorPlantsScreen(
    uiState: MonitorPlantsUiState
) {

}

@Preview
@Composable
fun MonitorPlantsScreenPreview() {
    AppTheme {
        MonitorPlantsScreen(
            uiState = MonitorPlantsUiState()
        )
    }
}