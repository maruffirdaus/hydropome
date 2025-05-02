package com.motion.hydropome.ui.personalization.model

import com.motion.hydropome.ui.personalization.constant.questions

data class PersonalizationUiState(
    val page: Int = 0,
    val selectedAnswers: Map<Int, Map<String, List<Boolean>>> = questions
        .mapIndexed { pageIndex, questionsOnPage ->
            pageIndex to questionsOnPage.associate { question ->
                question.id to question.answers.map { false }
            }
        }.toMap(),
    val isLoading: Boolean = false
)
