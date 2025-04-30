package com.motion.hydropome.ui.personalization.model

import androidx.annotation.DrawableRes

data class Question(
    val id: String,
    val title: String,
    val answers: List<String>,
    val isMultipleAnswer: Boolean = false,
    @DrawableRes val answerImages: List<Int>? = null
)
