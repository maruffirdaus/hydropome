package com.motion.hydropome.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motion.hydropome.R
import com.motion.hydropome.ui.AppDestination
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun OnboardingScreen(
    uiState: OnboardingUiState,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
    navController: NavController
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.background)
        ) {
            Column {
                val images = listOf(
                    R.drawable.img_onboarding_0,
                    R.drawable.img_onboarding_1
                )
                val headlines = listOf(
                    "Tanam Sayur Segar dari Rumah Tanpa Ribet! \uD83E\uDD55\uD83C\uDF45",
                    "Belanja Starter Kit & Jual Hasil Panen!"
                )
                val descriptions = listOf(
                    "Bersama HydropoMe, menanam sayuran untuk hidup lebih sehat dan hemat jadi lebih mudah!",
                    "Belanja, jual panen, dan penuhi kebutuhanmu di marketplace kami. Praktis banget buat kamu yang suka berkebun dari rumah!"
                )

                Image(
                    painter = painterResource(images[uiState.page]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .clip(BottomArcShape()),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.weight(0.1f))
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = headlines[uiState.page],
                        color = AppColors.textLight,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = descriptions[uiState.page],
                        color = AppColors.textLight,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.weight(0.2f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (0 < uiState.page) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFFFFFFF).copy(alpha = 0.2f))
                                .clickable(onClick = onPreviousPage),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_arrow_left),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                                tint = Color(0xFFE8F5F2)
                            )
                        }
                    } else {
                        Spacer(Modifier.width(42.dp))
                    }
                    Spacer(Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (uiState.page == 0) {
                                    Color(0xFF179778)
                                } else {
                                    Color(0xFFFFFFFF).copy(alpha = 0.2f)
                                }
                            )
                    )
                    Spacer(Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (uiState.page == 1) {
                                    Color(0xFF179778)
                                } else {
                                    Color(0xFFFFFFFF).copy(alpha = 0.2f)
                                }
                            )
                    )
                    Spacer(Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFFFFFFF).copy(alpha = 0.2f))
                            .clickable {
                                if (uiState.page < 1) {
                                    onNextPage()
                                } else {
                                    navController.navigate(AppDestination.Login)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_right),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Color(0xFFE8F5F2)
                        )
                    }
                }
                Spacer(Modifier.weight(0.2f))
                Spacer(Modifier.height(innerPadding.calculateBottomPadding()))
            }
            Box(
                modifier = Modifier
                    .padding(
                        top = innerPadding.calculateTopPadding() + 12.dp,
                        end = 20.dp
                    )
                    .width(64.dp)
                    .height(36.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE8F5F2))
                    .clickable {
                        navController.navigate(AppDestination.Login)
                    }
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Lewati",
                    color = Color(0xFF179778),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600
                )
            }
        }
    }
}

@Preview
@Composable
private fun OnboardingScreenPreview() {
    AppTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(),
            onNextPage = {},
            onPreviousPage = {},
            navController = rememberNavController()
        )
    }
}