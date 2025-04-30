package com.motion.hydropome.common.model

data class User(
    val name: String = "",
    val email: String = "",
    val preferences: Map<Int, List<Int>> = mapOf()
)
