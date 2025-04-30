package com.motion.hydropome.ui.personalization

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motion.hydropome.R
import com.motion.hydropome.ui.AppDestination
import com.motion.hydropome.ui.common.component.CustomButton
import com.motion.hydropome.ui.common.shape.BottomArcShape
import com.motion.hydropome.ui.personalization.component.QuestionCard
import com.motion.hydropome.ui.personalization.constant.questions
import com.motion.hydropome.ui.personalization.model.PersonalizationUiState
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun PersonalizationScreen(
    uiState: PersonalizationUiState,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
    onSelectedAnswerChange: (questionIndex: Int, selectedAnswers: List<Boolean>) -> Unit,
    onSavePreferences: (() -> Unit) -> Unit,
    navController: NavController
) {
    val scope = rememberCoroutineScope()

    Scaffold { innerPadding ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                val scrollState = rememberScrollState()

                AnimatedVisibility(
                    visible = scrollState.value == 0,
                    enter = fadeIn() + expandVertically(),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(if (uiState.page == 0) 0.4f else 0.3f)
                            .clip(BottomArcShape())
                            .background(AppColors.background)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(innerPadding)
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFE8F5F2).copy(alpha = 0.2f))
                                .clickable(onClick = {
                                    if (0 < uiState.page) {
                                        onPreviousPage()
                                    } else {
                                        navController.popBackStack(
                                            AppDestination.Personalization,
                                            true
                                        )
                                    }
                                }),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_arrow_left),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                                tint = Color(0xFFE8F5F2)
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "${uiState.page + 1}/3",
                            color = Color(0XFFE8F5F2),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W600
                        )
                        Spacer(Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .width(64.dp)
                                .height(36.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFE8F5F2).copy(alpha = 0.2f))
                                .clickable {
                                    navController.navigate(AppDestination.Main) {
                                        popUpTo(0)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Lewati",
                                color = Color(0xFFE8F5F2),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W600
                            )
                        }
                    }
                    Spacer(Modifier.height(if (uiState.page == 0) 32.dp else 40.dp))
                    if (uiState.page == 0) {
                        Text(
                            text = "Yuk Jawab Biar HydropoMe Tahu Kebutuhanmu \uD83D\uDC9A",
                            color = AppColors.textLight,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W700
                        )
                        Spacer(Modifier.height(28.dp))
                    }
                    questions[uiState.page].forEach { question ->
                        uiState.selectedAnswers[uiState.page]?.get(question.id)
                            ?.let { selectedAnswerState ->
                                QuestionCard(
                                    question = question,
                                    selectedAnswers = selectedAnswerState,
                                    onSelectedAnswersChange = { selectedAnswer ->
                                        onSelectedAnswerChange(question.id, selectedAnswer)
                                    },
                                )
                                Spacer(Modifier.height(20.dp))
                            }
                    }
                    CustomButton(
                        text = if (uiState.page < 2) "Selanjutnya" else "Simpan",
                        onClick = {
                            if (uiState.page < 2) {
                                onNextPage()
                                scope.launch {
                                    scrollState.scrollTo(0)
                                }
                            } else {
                                onSavePreferences {
                                    navController.navigate(AppDestination.Main) {
                                        popUpTo(0)
                                    }
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PersonalizationScreenPreview() {
    AppTheme {
        PersonalizationScreen(
            uiState = PersonalizationUiState(),
            onNextPage = {},
            onPreviousPage = {},
            onSelectedAnswerChange = { _, _ -> },
            onSavePreferences = {},
            navController = rememberNavController()
        )
    }
}