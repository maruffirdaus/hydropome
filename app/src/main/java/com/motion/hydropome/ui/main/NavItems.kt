package com.motion.hydropome.ui.main

import androidx.annotation.DrawableRes
import com.motion.hydropome.R

enum class NavItems(
    val title: String,
    @DrawableRes val icon: Int
) {
    HOME("Beranda", R.drawable.ic_home),
    MONITOR_PLANTS("Pantau Tanaman", R.drawable.ic_plant),
    MARKETPLACE("Marketplace", R.drawable.ic_bag),
    PROFILE("Profil", R.drawable.ic_profile)
}