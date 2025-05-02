package com.motion.hydropome.ui.personalization.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motion.hydropome.R
import com.motion.hydropome.ui.personalization.model.Question
import com.motion.hydropome.ui.theme.AppColors
import com.motion.hydropome.ui.theme.AppTheme

@Composable
fun QuestionCard(
    question: Question,
    selectedAnswers: List<Boolean>,
    onSelectedAnswersChange: (List<Boolean>) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Black.copy(alpha = 0.4f),
                spotColor = Color.Black.copy(alpha = 0.4f)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFFFFFFF))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ill_plant_circle),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = question.title,
                color = AppColors.text,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
        question.answers.forEachIndexed { index, answer ->
            val isSelected = selectedAnswers[index]

            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 48.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .let {
                        if (isSelected) {
                            it.border(
                                width = 1.dp,
                                color = Color(0xFF179778),
                                shape = RoundedCornerShape(10.dp)
                            )
                        } else {
                            it
                        }
                    }
                    .background(if (isSelected) Color(0xFFE8F5F2) else Color(0xFFF7F8F9))
                    .clickable {
                        val newSelectedAnswers = selectedAnswers.toMutableList()

                        if (isSelected) {
                            newSelectedAnswers[index] = false
                        } else {
                            if (question.isMultipleAnswer) {
                                newSelectedAnswers[index] = true
                            } else {
                                newSelectedAnswers.replaceAll { false }
                                newSelectedAnswers[index] = true
                            }
                        }
                        onSelectedAnswersChange(newSelectedAnswers)
                    }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = if (question.isMultipleAnswer) {
                        if (isSelected) {
                            painterResource(R.drawable.ic_checkbox_selected)
                        } else {
                            painterResource(R.drawable.ic_checkbox_unselected)
                        }
                    } else {
                        if (isSelected) {
                            painterResource(R.drawable.ic_radio_button_selected)
                        } else {
                            painterResource(R.drawable.ic_radio_button_unselected)
                        }
                    },
                    contentDescription = null,
                    modifier = Modifier.size(if (question.isMultipleAnswer) 24.dp else 20.dp),
                    tint = if (isSelected) AppColors.primary else Color(0xFFB4B5B6)
                )
                Spacer(Modifier.width(if (question.answerImages != null) 16.dp else 12.dp))
                question.answerImages?.let { answerImages ->
                    Image(
                        painter = painterResource(answerImages[index]),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(48.dp))
                }
                Text(
                    text = answer,
                    color = AppColors.text,
                    fontSize = 12.sp,
                    fontWeight = if (question.answerImages != null) FontWeight.W600 else FontWeight.W400,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
        }
    }
}

@Preview
@Composable
private fun QuestionCardPreview() {
    AppTheme {
        QuestionCard(
            question = Question(
                id = "0",
                title = "Apakah kamu pernah mencoba menanam hidroponik sebelumnya?",
                answers = listOf(
                    "Belum pernah",
                    "Pernah, tapi masih pemula",
                    "Sudah cukup berpengalaman"
                ),
            ),
            selectedAnswers = listOf(
                false,
                true,
                false
            ),
            onSelectedAnswersChange = {}
        )
    }
}