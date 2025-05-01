package com.motion.hydropome.ui.receipt.function

fun generateRandomID(length: Int): String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}