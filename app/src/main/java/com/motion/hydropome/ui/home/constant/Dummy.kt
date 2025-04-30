package com.motion.hydropome.ui.home.constant

import androidx.annotation.DrawableRes
import com.motion.hydropome.R
import com.motion.hydropome.common.type.Difficulty

data class Plant(
    @DrawableRes val image: Int,
    val title: String,
    val difficulty: Difficulty,
    val duration: String
)

val dummyPlants = listOf(
    Plant(R.drawable.onboarding_0, "Selada Hidroponik", Difficulty.EASY, "3-5"),
    Plant(R.drawable.onboarding_1, "Bayam Hidroponik", Difficulty.MEDIUM, "3-4"),
    Plant(R.drawable.onboarding_1, "Pakcoy Hidroponik", Difficulty.HARD, "4-5"),
    Plant(R.drawable.onboarding_0, "Tomat Cherry", Difficulty.EASY, "8-10"),
    Plant(R.drawable.onboarding_0, "Seledri Hidroponik", Difficulty.MEDIUM, "5-6"),
    Plant(R.drawable.onboarding_1, "Stroberi Hidroponik", Difficulty.HARD, "12-16"),
)