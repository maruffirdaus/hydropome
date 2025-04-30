package com.motion.hydropome.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun MainScreen(
    uiState: MainUiState,
    homeScreen: @Composable () -> Unit,
    monitorPlantsScreen: @Composable () -> Unit,
    marketplaceScreen: @Composable () -> Unit,
    profileScreen: @Composable () -> Unit,
    onSelectedNavItemChange: (Int) -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (uiState.selectedIndex) {
                    0 -> homeScreen()
                    1 -> monitorPlantsScreen()
                    2 -> marketplaceScreen()
                    3 -> profileScreen()
                }
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFFEDEDED)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(bottom = innerPadding.calculateBottomPadding()),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            ) {
                NavItems.entries.forEach { navItem ->
                    val isSelected = uiState.selectedIndex == navItem.ordinal
                    val contentColor = if (isSelected) {
                        AppColors.primary
                    } else {
                        Color(0xFF98A0AA)
                    }

                    Column(
                        modifier = Modifier
                            .height(52.dp)
                            .weight(1f)
                            .clickable(
                                indication = null,
                                interactionSource = MutableInteractionSource(),
                                onClick = {
                                    onSelectedNavItemChange(navItem.ordinal)
                                }
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(navItem.icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = contentColor
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = navItem.title,
                            color = contentColor,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) {
                                FontWeight.W600
                            } else {
                                FontWeight.W400
                            },
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    AppTheme {
        MainScreen(
            uiState = MainUiState(),
            homeScreen = {},
            monitorPlantsScreen = {},
            marketplaceScreen = {},
            profileScreen = {},
            onSelectedNavItemChange = {}
        )
    }
}